package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.gradetracker.Adapters.AssignmentAdapter;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.databinding.ActivityAssignmentBinding;

import java.util.ArrayList;

public class AssignmentActivity extends AppCompatActivity {
    private AppDatabase db;
    private static String COURSE_ID = "course_id";
    private ActivityAssignmentBinding activityAssignmentBinding;
    private int course_id;
    private ArrayList<Assignment> assignment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(getApplicationContext());
        activityAssignmentBinding = ActivityAssignmentBinding.inflate(getLayoutInflater());
        View view = activityAssignmentBinding.getRoot();
        setContentView(view);

//        createAssignments();
        db.AssignmentDao().deleteAllAssignment();
        Assignment assignment = new Assignment("Assignment 1", "Brief description", 100, 78, "02-15-2020", "02-17-2020", "Test", 1);
        db.AssignmentDao().insertAssignment(assignment);
//        AssignmentAdapter adapter = new AssignmentAdapter((assignment));
//        activityAssignmentBinding.rvAssignment.setAdapter(adapter);
//        activityAssignmentBinding.rvAssignment.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        course_id = intent.getIntExtra(COURSE_ID, -1);

        getOverallTestGrade();


    }

    public static Intent getIntent(Context context, int course_id) {
        Intent intent = new Intent(context, AssignmentActivity.class);
        intent.putExtra(COURSE_ID, course_id);
        return intent;
    }

    public ArrayList<Assignment> createAssignments() {

        db.AssignmentDao().insertAssignment(new Assignment("Assignment 1", "Brief description", 100, 78, "02-15-2020", "02-17-2020", "Test", 1));
        db.AssignmentDao().insertAssignment(new Assignment("Assignment 3", "Brief description", 100, 86, "02-15-2020", "02-17-2020", "Test", 1));
        db.AssignmentDao().insertAssignment(new Assignment("Assignment 2", "Brief description", 100, 65, "02-15-2020", "02-17-2020", "Test", 1));
        Log.d("TESTING", "Add assignments");
        return assignment;
    }

    public double getOverallTestGrade() {
        double overallTestGrade = 0.0;
        ArrayList<Assignment> testAssignments = (ArrayList<Assignment>) db.AssignmentDao().getAssignmentsWithCourseIdAndCategory(1, "Test");
        Log.d("TESTING", testAssignments.toString());
        for (Assignment assignment: testAssignments) {
            Log.d("TESTING", assignment.toString());
            overallTestGrade += assignment.getPercentageGrade();
        }
        Log.d("TESTING", Double.toString(overallTestGrade/testAssignments.size()) );
        return overallTestGrade/testAssignments.size();
    }





}