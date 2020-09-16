package com.example.gradetracker;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class CoursesActivity extends AppCompatActivity {
    private ActivityCoursesBinding activityCoursesBinding;
    private AppDatabase db;
    private User user;
    private CourseAdapter courseAdapter;
    List<Course> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(getApplicationContext());
        activityCoursesBinding = ActivityCoursesBinding.inflate(getLayoutInflater());
        View view = activityCoursesBinding.getRoot();
        setContentView(view);
        String username = getIntent().getStringExtra("username");
        user = db.userDao().getUserWithUsername(username);
        activityCoursesBinding.fabAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewCourseActivity.class);
                intent.putExtra("username", user.getUserName());
                startActivityForResult(intent, 1);
            }
        });
        courses = db.courseDao().getCoursesByUserId(user.getUserID());
        Log.d("CoursesActivity", courses.toString());
        if (courses.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "No courses to display. Feel free to add some!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            courseAdapter = new CourseAdapter(courses);
            activityCoursesBinding.rvCourses.setAdapter(courseAdapter);
            activityCoursesBinding.rvCourses.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public void onBackPressed() {
        // user can only sign out by clicking log out button
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            courses = db.courseDao().getCoursesByUserId(user.getUserID());
            courseAdapter = new CourseAdapter(courses);
            activityCoursesBinding.rvCourses.setAdapter(courseAdapter);
            activityCoursesBinding.rvCourses.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}