package algonquin.cst2335.hulf0002.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import algonquin.cst2335.hulf0002.data.MainViewModel;
import algonquin.cst2335.hulf0002.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has: " + s);
        });

        variableBinding.mybutton.setOnClickListener(click -> {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
        });

        Context context = getApplicationContext();

        model.isCoffeeDrinker.observe(this, selected -> {
            variableBinding.coffeeCheckbox.setChecked(selected);
            variableBinding.coffeeRadio.setChecked(selected);
            variableBinding.coffeeSwitch.setChecked(selected);
            CharSequence text = "The value is now: " + selected;
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        });

        variableBinding.coffeeCheckbox.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isCoffeeDrinker.postValue(isChecked);
        });
        variableBinding.coffeeRadio.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isCoffeeDrinker.postValue(isChecked);
        });
        variableBinding.coffeeSwitch.setOnCheckedChangeListener((btn, isChecked) -> {
            model.isCoffeeDrinker.postValue(isChecked);
        });

        variableBinding.clickableLogo.setOnClickListener( click -> {
            CharSequence text = "The width= " + click.getWidth() + " and height= " + click.getHeight();
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        });

    }
}
