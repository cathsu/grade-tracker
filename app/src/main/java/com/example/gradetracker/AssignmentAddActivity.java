package com.example.gradetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.AssignmentDao;
import com.example.gradetracker.Model.Assignment;

import java.security.SecureRandom;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class AssignmentAddActivity  extends AppCompatActivity{


    private EditText mAssignedDate;
    private EditText mDueDate;
    private EditText mEarnedPoints;
    private EditText mMaxPoints;
    private EditText mDescription;
    private EditText mCourseId;

    private RadioGroup mradioGroup;


    private AppDatabase db;

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_add);

        mAssignedDate = findViewById(R.id.tvAssignedDate);
        mDueDate = findViewById(R.id.tvDueDate);
        mEarnedPoints = findViewById(R.id.tvEarnedPoints);
        mMaxPoints = findViewById(R.id.tvMaxPoints);
        mDescription = findViewById(R.id.tvDescription);
        mCourseId = findViewById(R.id.tvCourseId);
        mradioGroup= findViewById(R.id.radioGroup);


        mButton = findViewById(R.id.button);
        db = AppDatabase.getInstance(getApplicationContext());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCourse();
            }
        });
    }

    void addCourse(){

        final String assignedDate = mAssignedDate.getText().toString();
        final String dueDate = mDueDate.getText().toString();
        final int earnedPoints = Integer.parseInt(mEarnedPoints.getText().toString());
        final int maxPoints = Integer.parseInt(mMaxPoints.getText().toString());
        final String description = mDescription.getText().toString();
        final int courseId = Integer.parseInt(mCourseId.getText().toString());
        int selectedRadioButton = mradioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedRadioButton);
        final String categoryName = radioButton.getText().toString();


        Assignment a = new Assignment(description, maxPoints, earnedPoints, assignedDate, dueDate, categoryName, courseId);

        db.AssignmentDao().insertAssignment(a);
        Toast toast = Toast.makeText(getApplicationContext(), "Assignment has been Added",Toast.LENGTH_LONG);
        toast.show();
    }
}
