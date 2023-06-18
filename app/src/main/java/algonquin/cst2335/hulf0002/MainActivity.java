package algonquin.cst2335.hulf0002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static String TAG ="MainActivty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);


        startActivity(nextPage);

        setContentView(R.layout.activity_main);
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