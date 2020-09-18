package com.example.gradetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private static String COURSE_ID = "course_id";
    private EditText mAssignedName;
    private EditText mAssignedDate;
    private EditText mDueDate;
    private EditText mEarnedPoints;
    private EditText mMaxPoints;
    private EditText mDescription;
    private EditText mCourseId;

    private RadioGroup mradioGroup;
    private AppDatabase db;

    private Integer course_id;

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_add);

        db = AppDatabase.getInstance(getApplicationContext());
        Intent intent = getIntent();
        course_id = intent.getIntExtra(COURSE_ID, -1);

        mAssignedName = findViewById(R.id.etAssignedName);
        mAssignedDate = findViewById(R.id.etAssignedDate);
        mDueDate = findViewById(R.id.etDueDate);
        mEarnedPoints = findViewById(R.id.etEarnedPoints);
        mMaxPoints = findViewById(R.id.etMaxPoints);
        mDescription = findViewById(R.id.etDescription);
        mradioGroup= findViewById(R.id.radioGroup);


        mButton = findViewById(R.id.button);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCourse();
            }
        });
    }

    private void addCourse(){

        String assignedName = mAssignedName.getText().toString();
        final String assignedDate = mAssignedDate.getText().toString();
        final String dueDate = mDueDate.getText().toString();
        final int earnedPoints = Integer.parseInt(mEarnedPoints.getText().toString());
        final int maxPoints = Integer.parseInt(mMaxPoints.getText().toString());
        final String description = mDescription.getText().toString();
        int selectedRadioButton = mradioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedRadioButton);
        final String categoryName = radioButton.getText().toString();


        Assignment a = new Assignment(assignedName, description, maxPoints, earnedPoints, assignedDate, dueDate, categoryName, course_id);
        Log.d("Assignment", a.toString());
        db.AssignmentDao().insertAssignment(a);
        Toast toast = Toast.makeText(getApplicationContext(), "Assignment has been Added",Toast.LENGTH_LONG);
        toast.show();

        Intent intent = AssignmentActivity.getIntent(getApplicationContext(), course_id);
        startActivity(intent);
    }

    public static Intent getIntent(Context context, int course_id) {
        Intent intent = new Intent(context, AssignmentAddActivity.class);
        intent.putExtra(COURSE_ID, course_id);
        return intent;
    }
}
