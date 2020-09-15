package com.example.gradetracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.Model.User;
import com.example.gradetracker.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private AppDatabase db;

    // For animation gradient transition
    AnimationDrawable animationDrawable;
    ConstraintLayout constraintLayout;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        // Gradient animation
        constraintLayout = (ConstraintLayout)findViewById(R.id.constraintLayout);
        animationDrawable = (AnimationDrawable)constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);


        db = AppDatabase.getInstance(getApplicationContext());

//        Assignment assignment = new Assignment("Assignment 1", "Brief description", 100, 78, "02-15-2020", "02-17-2020", "Test", 1);
//        db.AssignmentDao().insertAssignment(assignment);
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
                Intent intent = Signup.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
        activityMainBinding.assignmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AssignmentActivity.getIntent(getApplicationContext(), 1);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    public Boolean isUsernameUnique(String username) {
        User existingUser = db.userDao().getUserWithUsername(username);
        return existingUser == null;
    }

    public Boolean isPasswordMatching(String username, String password) {
        User existingUser = db.userDao().getUserWithUsername(username);
        return existingUser.getPassword().equals(password);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(animationDrawable != null && !animationDrawable.isRunning()){
            animationDrawable.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(animationDrawable != null && animationDrawable.isRunning()){
            animationDrawable.stop();
        }
    }
}