package com.example.gradetracker;

import android.os.Bundle;
import android.view.View;

import com.example.gradetracker.Adapters.CourseAdapter;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.Model.User;
import com.example.gradetracker.databinding.ActivityCoursesBinding;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

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
        courses = user.getCourses();

        // recycler view logic
        CourseAdapter adapter = new CourseAdapter(courses);
        activityCoursesBinding.rvCourses.setAdapter(adapter);
//        activityCoursesBinding.rvCourses
    }
}