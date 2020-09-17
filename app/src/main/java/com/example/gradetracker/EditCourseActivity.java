package com.example.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.databinding.ActivityEditCourseBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class EditCourseActivity extends AppCompatActivity {
    private ActivityEditCourseBinding activityEditCourseBinding;
    private AppDatabase db;
    private Course currentCourse;
    List<Course> courseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditCourseBinding = ActivityEditCourseBinding.inflate(getLayoutInflater());
        View view = activityEditCourseBinding.getRoot();
        setContentView(view);
        db = AppDatabase.getInstance(getApplicationContext());
        Integer courseId = getIntent().getIntExtra("courseId", 1);
        courseList = db.courseDao().getCourseId(courseId);
        currentCourse = courseList.get(0);
        setUpEditText(currentCourse);
        activityEditCourseBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCourse(currentCourse);
                Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
                String username = getIntent().getStringExtra("username");
                intent.putExtra("username", getIntent().getStringExtra("username"));
                assert username != null;
                startActivity(intent);
            }
        });
    }

    private void updateCourse(Course course) {
        String courseName = String.valueOf(activityEditCourseBinding.etCourseName.getText());
        String instructorName = String.valueOf(activityEditCourseBinding.etInstructor.getText());
        String startDate = String.valueOf(activityEditCourseBinding.etStartDate.getText());
        String endDate = String.valueOf(activityEditCourseBinding.etEndDate.getText());
        String description = String.valueOf(activityEditCourseBinding.etDescription.getText());
        course.setCourseName(courseName);
        course.setInstructor(instructorName);
        course.setStartDate(startDate);
        course.setEndDate(endDate);
        course.setDescription(description);
        db.courseDao().updateCourse(course);
    }

    private void setUpEditText(Course course) {
        String courseName = course.getCourseName();
        String instructorName = course.getInstructor();
        String startDate = course.getStartDate();
        String endDate = course.getEndDate();
        String description = course.getDescription();
        activityEditCourseBinding.etCourseName.setText(courseName);
        activityEditCourseBinding.etInstructor.setText(instructorName);
        activityEditCourseBinding.etStartDate.setText(startDate);
        activityEditCourseBinding.etEndDate.setText(endDate);
        activityEditCourseBinding.etDescription.setText(description);
    }
}