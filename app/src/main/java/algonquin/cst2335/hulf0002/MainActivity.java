package algonquin.cst2335.hulf0002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.ChangeTransform;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * This is the main class of the application and the first screen the user will see upon launch. It is a
 * password verification page.
 * @author Alex Hulford
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /**
     * this holds the text at the center of the screen
     */
    private TextView tvPassword = null;
    /**
     * this holds the edittext where the user can enter a password
     */
    private EditText etPassword = null;
    /**
     * this holds the button at the bottom of the screen
     */
    private Button loginButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPassword = findViewById(R.id.tvPassword);
        etPassword = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.loginButton);


        loginButton.setOnClickListener( click -> {
            String password = etPassword.getText().toString();
            if (checkPasswordComplexity(password)) {
                tvPassword.setText("Your password meets the requirements");
            } else {
                tvPassword.setText("You shall not pass!");
            }
        });


    }

    /**
     * function to test a user entered password to see if it contains an uppercase letter, a lowercase letter, a number, and a special
     * character. Iterates through each character of the password and returns true if it meets requirements
     * @param password user entered password from the edittext
     * @return returns true if password contains all required characters
     */
    boolean checkPasswordComplexity(String password){

        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for (char c : password.toCharArray()){
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
                break;
            }
        }

        if (!foundUpperCase){
            Toast.makeText(this, "Password missing uppercase letter", Toast.LENGTH_SHORT).show();
            return false;
        }

        for (char c : password.toCharArray()){
            if (Character.isLowerCase(c)) {
                foundLowerCase = true;
                break;
            }
        }

        if (!foundLowerCase){
            Toast.makeText(this, "Password missing lowercase letter", Toast.LENGTH_SHORT).show();
            return false;
        }

        for (char c : password.toCharArray()){
            if(Character.isDigit(c)) {
                foundNumber = true;
                break;
            }
        }

        if(!foundNumber){
            Toast.makeText(this, "Password missing number", Toast.LENGTH_SHORT).show();
            return false;
        }

        for (char c : password.toCharArray()){
            if(isSpecialCharacter(c)) {
                foundSpecial = true;
                break;
            }
        }

        if(!foundSpecial){
            Toast.makeText(this, "Password missing special character", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * checks a character against a set of special characters to see if it matches and returns true if so, otherwise
     * returns false
     * @param c the character to be checked against the special character cases
     * @return true if char c is a special character, false if not
     */
    boolean isSpecialCharacter(char c){
        switch (c){
            case '#':
            case '$' :
            case '%' :
            case '^':
            case '&' :
            case '*' :
            case '!' :
            case '@' :
            case '?' :
                return true;
            default:
                return false;
        }
    }
}