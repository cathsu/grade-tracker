package com.example.gradetracker;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.User;
import com.example.gradetracker.databinding.ActivityMainBinding;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private AppDatabase db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        db = AppDatabase.getInstance(getApplicationContext());
        activityMainBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = activityMainBinding.etUsername.getText().toString();
                String password = activityMainBinding.etPassword.getText().toString();
                if (!isUsernameUnique(username) && !username.isEmpty() && isPasswordMatching(username, password) && !password.isEmpty()) {
                    Intent loginToCoursesPageIntent = new Intent(getApplicationContext(), CoursesActivity.class);
                    loginToCoursesPageIntent.putExtra("username", username);
                    startActivity(loginToCoursesPageIntent);
                } else {
                    activityMainBinding.etUsername.setError("Invalid credentials");
                    activityMainBinding.etUsername.getBackground().setColorFilter(getResources().getColor(R.color.colorError),
                            PorterDuff.Mode.SRC_ATOP);
                    activityMainBinding.etPassword.setError("Invalid credentials");
                    activityMainBinding.etPassword.getBackground().setColorFilter(getResources().getColor(R.color.colorError),
                            PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        activityMainBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AssignmentActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    public Boolean isUsernameUnique(String username) {
        User existingUser = db.userDao().getUserWithUsername(username);
        return existingUser == null;
    }

    public Boolean isPasswordMatching(String username, String password) {
        User existingUser = db.userDao().getUserWithUsername(username);
        return existingUser.getPassword().equals(password);
    }
}