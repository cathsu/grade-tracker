package com.example.gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gradetracker.Adapters.AssignmentAdapter;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.databinding.ActivityAssignmentBinding;

import java.util.ArrayList;

public class AssignmentActivity extends AppCompatActivity {
    private AppDatabase db;
    private static String COURSE_ID = "course_id";
    private ActivityAssignmentBinding activityAssignmentBinding;
    private int course_id;

    private Button edit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(getApplicationContext());
        activityAssignmentBinding = ActivityAssignmentBinding.inflate(getLayoutInflater());
        View view = activityAssignmentBinding.getRoot();
        setContentView(view);


        Intent intent = getIntent();
        course_id = intent.getIntExtra(COURSE_ID, -1);
        Log.d("Course ID", Integer.toString(course_id));

        createAssignments();
        AssignmentAdapter adapter = new AssignmentAdapter((ArrayList<Assignment>) db.AssignmentDao().getAssignmentsWithCourseId(1));
        activityAssignmentBinding.rvAssignment.setAdapter(adapter);
        activityAssignmentBinding.rvAssignment.setLayoutManager(new LinearLayoutManager(this));


        getOverallGrade();


    }


    public static Intent getIntent(Context context, int course_id) {
        Intent intent = new Intent(context, AssignmentActivity.class);
        intent.putExtra(COURSE_ID, course_id);
        return intent;
    }

    public void createAssignments() {
        db.AssignmentDao().deleteAllAssignment();
        db.AssignmentDao().insertAssignment(new Assignment("Assignment 1", "Brief description", 100, 78, "02-15-2020", "02-17-2020", "Test", course_id));
        db.AssignmentDao().insertAssignment(new Assignment("Assignment 2", "Brief description", 100, 86, "02-15-2020", "02-17-2020", "Test", course_id));
        db.AssignmentDao().insertAssignment(new Assignment("Assignment 3", "Brief description", 100, 65, "02-15-2020", "02-17-2020", "Test", course_id));

        db.AssignmentDao().insertAssignment(new Assignment("Assignment 4", "Brief description", 10, 8, "02-15-2020", "02-17-2020", "Quiz", course_id));
        db.AssignmentDao().insertAssignment(new Assignment("Assignment 5", "Brief description", 10, 3, "02-15-2020", "02-17-2020", "Quiz", course_id));
        db.AssignmentDao().insertAssignment(new Assignment("Assignment 6", "Brief description", 10, 5, "02-15-2020", "02-17-2020", "Quiz", course_id));

        db.AssignmentDao().insertAssignment(new Assignment("Assignment 7", "Brief description", 20, 8, "02-15-2020", "02-17-2020", "Lab", course_id));
        db.AssignmentDao().insertAssignment(new Assignment("Assignment 8", "Brief description", 20, 16, "02-15-2020", "02-17-2020", "Lab", course_id));
        db.AssignmentDao().insertAssignment(new Assignment("Assignment 9", "Brief description", 20, 20, "02-15-2020", "02-17-2020", "Lab", course_id));

        db.AssignmentDao().insertAssignment(new Assignment("Assignment 10", "Brief description", 30, 30, "02-15-2020", "02-17-2020", "Homework", course_id));
        db.AssignmentDao().insertAssignment(new Assignment("Assignment 11", "Brief description", 30, 15, "02-15-2020", "02-17-2020", "Homework", course_id));
        db.AssignmentDao().insertAssignment(new Assignment("Assignment 12", "Brief description", 30, 26, "02-15-2020", "02-17-2020", "Homework", course_id));


    }

    public double getOverallGrade() {
        double testGrade = getOverallTestGrade();
        double quizGrade = getOverallQuizGrade();
        double labGrade = getOverallLabGrade();
        double homeworkGrade = getOverallHomeworkGrade();

        double overallGrade = (0.4 *testGrade) + (0.2 * quizGrade) + (0.3 *homeworkGrade) + (0.1*labGrade);
        Log.d("Test Grade", Double.toString(testGrade));
        Log.d("Quiz Grade", Double.toString(quizGrade));
        Log.d("Lab Grade", Double.toString(labGrade));
        Log.d("Homework Grade", Double.toString(homeworkGrade));
        Log.d("Overall Grade", Double.toString(overallGrade));
        return overallGrade;
    }

    public double getOverallTestGrade() {
        double totalTestGrades = 0.0;
        ArrayList<Assignment> testAssignments = (ArrayList<Assignment>) db.AssignmentDao().getAssignmentsWithCourseIdAndCategory(course_id, "Test");
        for (Assignment assignment: testAssignments) {
            totalTestGrades += assignment.getPercentageGrade();
        }
        return totalTestGrades/testAssignments.size();
    }

    public double getOverallQuizGrade() {
        double totalQuizGrades = 0.0;
        ArrayList<Assignment> quizAssignments = (ArrayList<Assignment>) db.AssignmentDao().getAssignmentsWithCourseIdAndCategory(course_id, "Quiz");

        for (Assignment assignment: quizAssignments) {
            totalQuizGrades += assignment.getPercentageGrade();
        }
        return totalQuizGrades/quizAssignments.size();

    }

    public double getOverallLabGrade() {
        double totalLabGrades = 0.0;
        ArrayList<Assignment> labAssignments = (ArrayList<Assignment>) db.AssignmentDao().getAssignmentsWithCourseIdAndCategory(course_id, "Lab");

        for (Assignment assignment: labAssignments) {
            totalLabGrades += assignment.getPercentageGrade();
        }
        return totalLabGrades/labAssignments.size();

    }

    public double getOverallHomeworkGrade() {
        double totalHomeworkGrades = 0.0;
        ArrayList<Assignment> homeworkAssignments = (ArrayList<Assignment>) db.AssignmentDao().getAssignmentsWithCourseIdAndCategory(course_id, "Homework");
        for (Assignment assignment: homeworkAssignments) {
            totalHomeworkGrades += assignment.getPercentageGrade();
        }
        return totalHomeworkGrades/homeworkAssignments.size();

    }
}