package com.example.gradetracker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "testing";

    private Button mSignup;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSignup = findViewById(R.id.SignUp);
    }

    public void signup(View view) {
        Intent intent = Signup.getIntent(getApplicationContext());
        startActivity(intent);
    }
}