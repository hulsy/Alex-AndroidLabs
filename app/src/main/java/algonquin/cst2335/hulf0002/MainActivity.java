package algonquin.cst2335.hulf0002;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.transition.ChangeTransform;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import algonquin.cst2335.hulf0002.databinding.ActivityMainBinding;

/**
 * This is the main class of the application and the first screen the user will see upon launch.
 * @author Alex Hulford
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    RequestQueue queue = null;
    String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        queue = Volley.newRequestQueue(this);
        ActivityMainBinding binding = ActivityMainBinding.inflate( getLayoutInflater() );
        setContentView(  binding.getRoot()  );

        binding.forecastButton.setOnClickListener(click -> {
            cityName = binding.etCity.getText().toString();
            String stringURL = null;
            try {
                stringURL = "https://api.openweathermap.org/data/2.5/weather?q="
                            + URLEncoder.encode(cityName, "UTF-8")
                            + "&appid=7e943c97096a9784391a981c4d878b22&units=metric";
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                    (response) -> {

                        JSONObject main = null;
                        try {
                            main = response.getJSONObject("main");
                            double current = main.getDouble("temp");
                            double min = main.getDouble("temp_min");
                            double max = main.getDouble("temp_max");
                            int humidity = main.getInt("humidity");

                            runOnUiThread( (  )  -> {

                                binding.current.setText("The current temperature is " + current + " degrees");
                                binding.current.setVisibility(View.VISIBLE);

                                binding.maxTemp.setText("The max temperature is " + max + " degrees");
                                binding.maxTemp.setVisibility(View.VISIBLE);

                                binding.minTemp.setText("The min temperature is " + min + " degrees");
                                binding.minTemp.setVisibility(View.VISIBLE);

                                binding.humidity.setText("The humidity is " + humidity + " degrees");
                                binding.humidity.setVisibility(View.VISIBLE);
                            });


                            JSONArray weatherArray = response.getJSONArray ( "weather" );
                            JSONObject position0 = weatherArray.getJSONObject(0);

                            String description = position0.getString("description");
                            String iconName = position0.getString("icon");
                            String imageUrl = "http://openweathermap.org/img/w/" + iconName + ".png";

                            runOnUiThread( (  )  -> {
                                binding.description.setText(description);
                                binding.description.setVisibility(View.VISIBLE);
                            });

                            String pathname = getFilesDir() + "/" + iconName + ".png";
                            File file = new File(pathname);
                            Bitmap image;
                            if(file.exists()){
                                image = BitmapFactory.decodeFile(pathname);
                            }

                            ImageRequest imgReq = new ImageRequest(imageUrl, bitmap -> {
                                // Do something with loaded bitmap...
                                runOnUiThread( (  )  -> {
                                    binding.icon.setImageBitmap(bitmap);
                                    binding.icon.setVisibility(View.VISIBLE);
                                });

                                try {
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, MainActivity.this.openFileOutput(iconName + ".png", Activity.MODE_PRIVATE));

                                } catch (IOException e) {
                                    e.printStackTrace();

                                }

                            }, 1024, 1024, ImageView.ScaleType.CENTER, null,
                                    (error ) -> {

                            });
                            queue.add(imgReq);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    },
                    (error) -> {});
            queue.add(request);
        });




    }

}