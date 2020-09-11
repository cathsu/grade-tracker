package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gradetracker.Adapters.AssignmentAdapter;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.databinding.ActivityAssignmentBinding;

import java.util.ArrayList;

public class AssignmentActivity extends AppCompatActivity {
    private AppDatabase db;
    private ActivityAssignmentBinding activityAssignmentBinding;

    private ArrayList<Assignment> assignment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(getApplicationContext());
        activityAssignmentBinding = ActivityAssignmentBinding.inflate(getLayoutInflater());
        View view = activityAssignmentBinding.getRoot();
        setContentView(view);

        assignment = createAssignments(assignment);
        AssignmentAdapter adapter = new AssignmentAdapter((assignment));
        activityAssignmentBinding.rvAssignment.setAdapter(adapter);
        activityAssignmentBinding.rvAssignment.setLayoutManager(new LinearLayoutManager(this));

    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, AssignmentActivity.class);
        return intent;
    }

    public ArrayList<Assignment> createAssignments(ArrayList<Assignment> assignment) {
        assignment.add(new Assignment("Assignment 1", 100, 78, "02-15-2020", "02-17-2020", "Test", 1));
        assignment.add(new Assignment("Assignment 2", 100, 78, "02-15-2020", "02-17-2020", "Test", 1));
        assignment.add(new Assignment("Assignment 3", 100, 78, "02-15-2020", "02-17-2020", "Test", 1));
        return assignment;
    }



}