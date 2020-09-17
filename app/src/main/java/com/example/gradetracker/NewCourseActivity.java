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
    private ActivityNewCourseBinding activityNewCourseBinding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewCourseBinding = ActivityNewCourseBinding.inflate(getLayoutInflater());
        View view = activityNewCourseBinding.getRoot();
        setContentView(view);
        final AppDatabase db = AppDatabase.getInstance(getApplicationContext());
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
                Course newCourse = new Course(userId, instructorName, courseName, description, startDate, endDate, null);
                db.courseDao().insertCourse(newCourse);
                Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
                String username = getIntent().getStringExtra("username");
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}