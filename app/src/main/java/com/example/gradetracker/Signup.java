package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.Model.User;

import java.util.ArrayList;

public class Signup extends AppCompatActivity {

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mUsername;
    private EditText mPassword;
    private Button mSignupButton;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirstName = findViewById(R.id.editTextFirstName);
        mLastName = findViewById(R.id.editTextLastName);
        mUsername = findViewById(R.id.editTextUsername);
        mPassword = findViewById(R.id.editTextPassword);
        mSignupButton = findViewById(R.id.buttonSignup);
        db = AppDatabase.getInstance(getApplicationContext());

    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, Signup.class);
        return intent;
    }

    public void signup(View view) {
        validateCredentials();
    }

    public void validateCredentials() {
        String firstName = mFirstName.getText().toString();
        String lastName = mLastName.getText().toString();
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        Boolean isUsernameValid = isUsernameUnique(username);
        Boolean isPasswordValid = doesPasswordMeetRequirements(password);


        if (isUsernameValid && isPasswordValid && !firstName.isEmpty() && !lastName.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
            db.userDao().insertUser(new User(username, password, firstName, lastName, new ArrayList<Course>()));
            Toast t = Toast.makeText(getApplicationContext(), "Signup successful", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.BOTTOM, 0, 0);
            t.show();
            Intent intent = MainActivity.getIntent(getApplicationContext());
            startActivity(intent);
        }
        if (firstName.isEmpty()) {
            mFirstName.setError("Please enter a first name");
        }
        if (lastName.isEmpty()) {
            mLastName.setError("Please enter a last name");
        }
        if (username.isEmpty()) {
            mUsername.setError("Please enter a username");
        }
        if (password.isEmpty()) {
            mPassword.setError("Please enter a password");
        }
        else {
            if (!isUsernameValid) {
                mUsername.setError("Username is already taken.");
            }
            if (!isPasswordValid) {
                mPassword.setError("Password must be 8 characters long. There must be at least 3 letters and 2 numbers.");
            }
        }
//        Log.d("testing", db.userDao().getUserWithUsername(username).toString());


    }


    public Boolean isUsernameUnique(String username) {
        User existingUser = db.userDao().getUserWithUsername(username);
        return existingUser == null? true : false;
    }

    public Boolean doesPasswordMeetRequirements(String password) {
        int numLetters = 0, numDigits = 0;
        if (password.length() < 8) {
            return false;
        }

        for (int i = 0; i<password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) {
                numLetters += 1;
            }
            else if (Character.isDigit(password.charAt(i))) {
                numDigits += 1;
            }
        }
        return (numLetters >= 3 && numDigits >= 2) ? true : false;
    }
}