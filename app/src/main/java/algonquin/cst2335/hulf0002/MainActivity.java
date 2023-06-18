package algonquin.cst2335.hulf0002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static String TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating an intent to switch to the second activity page
        Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);

        //Creating variables for my views
        Button loginButton = findViewById(R.id.loginButton);
        EditText email = findViewById(R.id.emailEdit);

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String emailAddress = prefs.getString("LoginName", "");
        email.setText(emailAddress);


        //setting an onclick listener on my login button that switches to the second activity
        loginButton.setOnClickListener(click -> {
            String enteredEmail = email.getText().toString();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("LoginName", enteredEmail);
            editor.commit();

            nextPage.putExtra("EmailAddress", enteredEmail);
            startActivity(nextPage);
        });







        Log.w(TAG, "In onCreate() - Loading Widgets");

        
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG, "The application is now visible on screen.");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "The application is now responding to user input");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG, "The application is no longer visible.");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "Any memory used by the application is freed.");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "The application no longer responds to user input");
    }


}