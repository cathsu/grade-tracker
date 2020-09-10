package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.User;

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

    public static Intent getIntent(Context context, String value) {
        Intent intent = new Intent(context, Signup.class);
        return intent;
    }

    public void signup(View view) {


    }

    public Boolean validateCredentials() {
        String firstName = mFirstName.getText().toString();
        String lastName = mLastName.getText().toString();
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();


        return false;
    }

    public Boolean isUsernameUnique(String username) {
        User existingUser = db.userDao().getUserWithUsername(username);
        return existingUser == null? true : false;
    }

//    public Boolean isUsernameUnique(User existingUser) {
//
//        return existingUser == null? true : false;
//    }
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

    }

}