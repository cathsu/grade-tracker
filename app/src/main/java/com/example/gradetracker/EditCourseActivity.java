package com.example.gradetracker;

import android.os.Bundle;
import android.view.View;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.databinding.ActivityEditCourseBinding;

import androidx.appcompat.app.AppCompatActivity;

public class EditCourseActivity extends AppCompatActivity {
    private ActivityEditCourseBinding activityEditCourseBinding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditCourseBinding = ActivityEditCourseBinding.inflate(getLayoutInflater());
        View view = activityEditCourseBinding.getRoot();
        setContentView(view);
        db = AppDatabase.getInstance(getApplicationContext());
    }


}