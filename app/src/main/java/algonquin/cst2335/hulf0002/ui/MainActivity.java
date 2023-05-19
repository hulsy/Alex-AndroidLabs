package algonquin.cst2335.hulf0002.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        variableBinding.textview.setText(model.editString);
        variableBinding.mybutton.setOnClickListener(click -> {
            model.editString = variableBinding.myedittext.getText().toString();
            variableBinding.textview.setText("Your edit text has: " + model.editString);
        });

    }


}
