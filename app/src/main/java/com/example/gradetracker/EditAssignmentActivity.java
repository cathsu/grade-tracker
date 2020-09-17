package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.databinding.ActivityEditAssignmentBinding;


//Citation: https://stackoverflow.com/questions/20138445/how-to-get-the-text-from-radio-button-in-a-radio-group-when-radio-button-checked
public class EditAssignmentActivity extends AppCompatActivity {

    private ActivityEditAssignmentBinding activityEditAssignmentBinding;
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String  EARNED_POINTS = "earnedPoints";
    private static final String MAX_POINTS = "maxPoints";
    private static final String ASSIGNED_DATE = "assignedDate";
    private static final String DUE_DATE = "dueDate";
    private static final String CATEGORY = "category";
    private static final String COURSE_ID = "course_id";
    private static final String ASSIGNMENT_ID = "course_id";
    private RadioButton categoryButton;
    private AppDatabase db;
    private Integer course_id;
    private Integer assignment_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditAssignmentBinding = ActivityEditAssignmentBinding.inflate(getLayoutInflater());
        View view = activityEditAssignmentBinding.getRoot();
        Intent intent = getIntent();
        activityEditAssignmentBinding.editTextAssignmentName.setText(intent.getStringExtra(NAME));
        activityEditAssignmentBinding.editTextDescription.setText(intent.getStringExtra(DESCRIPTION));
        activityEditAssignmentBinding.editTextEarnedPoints.setText(Integer.toString(intent.getIntExtra(EARNED_POINTS, -1)));
        activityEditAssignmentBinding.editTextMaxPoints.setText(Integer.toString(intent.getIntExtra(MAX_POINTS, -1)));
        activityEditAssignmentBinding.editTextAssignedDate.setText(intent.getStringExtra(ASSIGNED_DATE));
        activityEditAssignmentBinding.editTextDueDate.setText(intent.getStringExtra(DUE_DATE));

        checkCategoryButton(intent.getStringExtra(CATEGORY));
        course_id = intent.getIntExtra(COURSE_ID, -1);
        assignment_id = intent.getIntExtra(ASSIGNMENT_ID, -1);
        setContentView(view);
        db = AppDatabase.getInstance(getApplicationContext());
    }


    public static Intent getIntent(Context context, Integer course_id, Integer assignment_id, String name, String description, Integer earnedPoints, Integer maxPoints, String assignedDate, String dueDate, String category) {
        Intent intent = new Intent(context, EditAssignmentActivity.class);
        intent.putExtra(COURSE_ID, course_id);
        intent.putExtra(ASSIGNMENT_ID, assignment_id);
        intent.putExtra(NAME, name);
        intent.putExtra(DESCRIPTION, description);
        intent.putExtra(EARNED_POINTS, earnedPoints);
        intent.putExtra(MAX_POINTS, maxPoints);
        intent.putExtra(ASSIGNED_DATE, assignedDate);
        intent.putExtra(DUE_DATE, dueDate);
        intent.putExtra(CATEGORY, category);
        return intent;
    }


    public void edit(View view) {
        String name = activityEditAssignmentBinding.editTextAssignmentName.getText().toString();
        String description = activityEditAssignmentBinding.editTextDescription.getText().toString();
        String earnedPointsString = activityEditAssignmentBinding.editTextEarnedPoints.getText().toString();
        String maxPointsString = activityEditAssignmentBinding.editTextMaxPoints.getText().toString();
        String assignedDate = activityEditAssignmentBinding.editTextAssignedDate.getText().toString();
        String dueDate = activityEditAssignmentBinding.editTextDueDate.getText().toString();
        Integer categoryId = activityEditAssignmentBinding.categoryButtonGroup.getCheckedRadioButtonId();


        Boolean validEdit = isEditValid(name, description, earnedPointsString, maxPointsString, assignedDate, dueDate);
        if (validEdit) {
            Assignment originalAssignment = db.AssignmentDao().getAssignmentWithId(assignment_id);
            originalAssignment.setName(name);
            originalAssignment.setAssignmentDescription(description);
            originalAssignment.setEarnedPoints(Integer.parseInt(earnedPointsString));
            originalAssignment.setAssignedDate(assignedDate);
            originalAssignment.setDueDate(dueDate);
            categoryButton = findViewById(activityEditAssignmentBinding.categoryButtonGroup.getCheckedRadioButtonId());
            originalAssignment.setCategoryName(categoryButton.getText().toString());
//            Log.d("Integer", Integer.toString(originalAssignment.getCourseID()));
            db.AssignmentDao().updateAssignment(originalAssignment);
            Intent intent = AssignmentActivity.getIntent(getApplicationContext(), course_id);
            startActivity(intent);
        }
    }

    private void checkCategoryButton(String category) {
        String testText = activityEditAssignmentBinding.testButton.getText().toString();
        if (activityEditAssignmentBinding.testButton.getText().toString().equals(category)) {
            activityEditAssignmentBinding.testButton.setChecked(true);
        }
        else if (activityEditAssignmentBinding.quizButton.getText().toString().equals(category)) {
            activityEditAssignmentBinding.quizButton.setChecked(true);
        }
        else if (activityEditAssignmentBinding.labButton.getText().toString().equals(category)) {
            activityEditAssignmentBinding.labButton.setChecked(true);
        }
        else if (activityEditAssignmentBinding.homeworkButton.getText().toString().equals(category)) {
            activityEditAssignmentBinding.homeworkButton.setChecked(true);
        }
    }

    private boolean isEditValid(String name, String description, String earnedPoints, String maxPoints, String assignedDate, String dueDate) {
        if (name.isEmpty() || description.isEmpty() || earnedPoints.isEmpty() || maxPoints.isEmpty() || assignedDate.isEmpty() || dueDate.isEmpty()) {
            if (name.isEmpty()) {
                activityEditAssignmentBinding.editTextAssignmentName.setError("Please enter a name");
            }
            if (description.isEmpty()) {
                activityEditAssignmentBinding.editTextDescription.setError("Please enter a description");
            }
            if (earnedPoints.isEmpty()) {
                activityEditAssignmentBinding.editTextEarnedPoints.setError("Please enter the points you earned");
            }
            if (maxPoints.isEmpty()) {
                activityEditAssignmentBinding.editTextMaxPoints.setError("Please enter the max points");
            }
            if (assignedDate.isEmpty()) {
                activityEditAssignmentBinding.editTextAssignedDate.setError("Please enter the assigned date");
            }
            if (dueDate.isEmpty()) {
                activityEditAssignmentBinding.editTextDueDate.setError("Please enter the due date");
            }
            return false;
        } else {
            return true;
        }
    }

}