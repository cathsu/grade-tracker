package com.example.gradetracker;

import android.os.Bundle;
import android.view.View;

import com.example.gradetracker.databinding.ActivityNewCourseBinding;

import androidx.appcompat.app.AppCompatActivity;

public class NewCourseActivity extends AppCompatActivity {
    private ActivityNewCourseBinding activityNewCourseBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewCourseBinding = ActivityNewCourseBinding.inflate(getLayoutInflater());
        View view = activityNewCourseBinding.getRoot();
        setContentView(view);
//        activityNewCourseBinding.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(NewCourseActivity.this, activityNewCourseBinding.tietCourseName.getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}