package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.databinding.ActivityEditAssignmentBinding;


public class EditAssignmentActivity extends AppCompatActivity {

    private ActivityEditAssignmentBinding activityEditAssignmentBinding;
    private static final String ASSIGNMENT_ID = "course_id";
    private RadioButton categoryButton;
    private AppDatabase db;
    private Assignment assignment;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditAssignmentBinding = ActivityEditAssignmentBinding.inflate(getLayoutInflater());
        View view = activityEditAssignmentBinding.getRoot();
        Intent intent = getIntent();
        db = AppDatabase.getInstance(getApplicationContext());
        assignment = db.AssignmentDao().getAssignmentWithId(intent.getIntExtra(ASSIGNMENT_ID, -1));
        activityEditAssignmentBinding.editTextAssignmentName.setText(assignment.getName());
        activityEditAssignmentBinding.editTextDescription.setText(assignment.getAssignmentDescription());
        activityEditAssignmentBinding.editTextEarnedPoints.setText(Integer.toString(assignment.getEarnedPoints()));
        activityEditAssignmentBinding.editTextMaxPoints.setText(Integer.toString(assignment.getMaxPoints()));
        activityEditAssignmentBinding.editTextAssignedDate.setText(assignment.getAssignedDate());
        activityEditAssignmentBinding.editTextDueDate.setText(assignment.getDueDate());
        checkCategoryButton(assignment.getCategoryName());

        setContentView(view);

    }


    /**
     * This method launches the EditAssignmentActivity. It passes in the id of the assignment as an extra.
     * @param context
     * @param assignment_id
     * @return an intent
     */
    public static Intent getIntent(Context context, Integer assignment_id) {
        Intent intent = new Intent(context, EditAssignmentActivity.class);
        intent.putExtra(ASSIGNMENT_ID, assignment_id);
        return intent;
    }


    /**
     * This method is the onClick handler for the Edit button. If the user's edits are valid, this method will update the assignment with its new attributes
     * and redirect to the Assignment page.
     * @param view
     */
    public void edit(View view) {
        String name = activityEditAssignmentBinding.editTextAssignmentName.getText().toString();
        String description = activityEditAssignmentBinding.editTextDescription.getText().toString();
        String earnedPointsString = activityEditAssignmentBinding.editTextEarnedPoints.getText().toString();
        String maxPointsString = activityEditAssignmentBinding.editTextMaxPoints.getText().toString();
        String assignedDate = activityEditAssignmentBinding.editTextAssignedDate.getText().toString();
        String dueDate = activityEditAssignmentBinding.editTextDueDate.getText().toString();

        Boolean validEdit = isEditValid(name, description, earnedPointsString, maxPointsString, assignedDate, dueDate);
        if (validEdit) {
            assignment.setName(name);
            assignment.setAssignmentDescription(description);
            assignment.setEarnedPoints(Integer.parseInt(earnedPointsString));
            assignment.setMaxPoints(Integer.parseInt(maxPointsString));
            assignment.setAssignedDate(assignedDate);
            assignment.setDueDate(dueDate);
            categoryButton = findViewById(activityEditAssignmentBinding.categoryButtonGroup.getCheckedRadioButtonId());
            assignment.setCategoryName(categoryButton.getText().toString());
            db.AssignmentDao().updateAssignment(assignment);
            Intent intent = AssignmentActivity.getIntent(getApplicationContext(), assignment.getCourseID());
            startActivity(intent);
        }
    }

    /**
     * This method checks the button that matches the category the assignment is currently under
     * @param category
     */
    private void checkCategoryButton(String category) {
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

    /**
     * This method checks to make sure that data has been entered into all of the assignment fields. It will display an error message for the fields that are empty.
     * @param name
     * @param description
     * @param earnedPoints
     * @param maxPoints
     * @param assignedDate
     * @param dueDate
     * @return true if none of the fields are empty, false otherwise
     */
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