package com.example.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.Model.User;
import com.example.gradetracker.databinding.ActivityNewCourseBinding;

import androidx.appcompat.app.AppCompatActivity;

public class NewCourseActivity extends AppCompatActivity {
    private static final String TAG = "NewCourseActivity";
    private AppDatabase db;
    private ActivityNewCourseBinding activityNewCourseBinding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewCourseBinding = ActivityNewCourseBinding.inflate(getLayoutInflater());
        View view = activityNewCourseBinding.getRoot();
        setContentView(view);
        db = AppDatabase.getInstance(getApplicationContext());
        String username = getIntent().getStringExtra("username");
        user = db.userDao().getUserWithUsername(username);
        activityNewCourseBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseName = String.valueOf(activityNewCourseBinding.etCourseName.getText());
                String instructorName = String.valueOf(activityNewCourseBinding.etInstructor.getText());
                String startDate = String.valueOf(activityNewCourseBinding.etStartDate.getText());
                String endDate = String.valueOf(activityNewCourseBinding.etEndDate.getText());
                String description = String.valueOf(activityNewCourseBinding.etDescription.getText());
                Integer userId = user.getUserID();
                db.courseDao().insertCourse(new Course(userId, instructorName, courseName, description, startDate, endDate, null));
                finish();
            }
        });
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("added", user.getUserName());
        setResult(1, intent);
        super.finish();
    }
}