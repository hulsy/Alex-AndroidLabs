package algonquin.cst2335.hulf0002;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        ImageView profileImage = findViewById(R.id.profileImageView);
        Button callButton = findViewById(R.id.callButton);
        EditText phoneNumber = findViewById(R.id.editTextPhone);
        TextView welcomeMessage = findViewById(R.id.tvWelcome);

        welcomeMessage.setText("Welcome: " + emailAddress);


        callButton.setOnClickListener(click -> {
            String enteredPhoneNum = phoneNumber.getText().toString();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("PhoneNumber", enteredPhoneNum);
            editor.commit();

        });


        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:" + phoneNumber));

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // android 33 requires below permissions code
//        if(checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
//            startActivity(cameraIntent);
//        else
//            requestPermissions(new String[] {Manifest.permission.CAMERA}, 20);

        ActivityResultLauncher <Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    Bitmap thumbnail = data.getParcelableExtra("data");
                    profileImage.setImageBitmap(thumbnail);

                    FileOutputStream fOut = null;
                    try {
                        fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
                        thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                        fOut.flush();
                        fOut.close();
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        cameraResult.launch(cameraIntent);
        File file = new File(getFilesDir(),"Picture.png");
        if(file.exists()){
            Bitmap theImage = BitmapFactory.decodeFile("Picture.png");
            profileImage.setImageBitmap(theImage);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        EditText phoneNumber = findViewById(R.id.editTextPhone);
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String phoneNumberText = prefs.getString("PhoneNumber", "");
        phoneNumber.setText(phoneNumberText);
    }
}