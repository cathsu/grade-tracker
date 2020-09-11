package com.example.gradetracker;

import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


import com.example.gradetracker.Adapters.CourseAdapter;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.Model.User;
import com.example.gradetracker.databinding.ActivityCoursesBinding;

import java.time.LocalDate;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class CoursesActivity extends AppCompatActivity {
    private ActivityCoursesBinding activityCoursesBinding;
    private AppDatabase db;
    ArrayList<Course> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(getApplicationContext());
        activityCoursesBinding = ActivityCoursesBinding.inflate(getLayoutInflater());
        View view = activityCoursesBinding.getRoot();
        setContentView(view);
        String username = getIntent().getStringExtra("username");
        User user = db.userDao().getUserWithUsername(username);
        if (courses.isEmpty()) {
            Log.d("testing", "empty");
            Toast toast = Toast.makeText(getApplicationContext(), "No courses to display. Feel free to add some!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            CourseAdapter adapter = new CourseAdapter(courses);
            activityCoursesBinding.rvCourses.setAdapter(adapter);
            activityCoursesBinding.rvCourses.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}