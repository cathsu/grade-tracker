package com.example.gradetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.gradetracker.Adapters.AssignmentAdapter;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.databinding.ActivityAssignmentBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AssignmentActivity extends AppCompatActivity {
    private AppDatabase db;
    private static String COURSE_ID = "course_id";
    private ActivityAssignmentBinding activityAssignmentBinding;
    private int course_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAssignmentBinding = ActivityAssignmentBinding.inflate(getLayoutInflater());
        View view = activityAssignmentBinding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        course_id = intent.getIntExtra(COURSE_ID, -1);
        db = AppDatabase.getInstance(getApplicationContext());
        Course course = db.courseDao().getCourseById(course_id);

        DecimalFormat decimalFormat = new DecimalFormat("##.##");

        activityAssignmentBinding.tvCourseName.setText(course.getCourseName());
        activityAssignmentBinding.tvInstructor.setText(course.getInstructor());
        activityAssignmentBinding.tvDescription.setText(course.getDescription());
        activityAssignmentBinding.tvStartDate.setText(course.getStartDate());
        activityAssignmentBinding.tvEndDate.setText(course.getEndDate());
        activityAssignmentBinding.tvCourseGrade.setText("Overall Grade: " + decimalFormat.format(getOverallGrade()) + "%");
        activityAssignmentBinding.tvTestGrade.setText("Test Grade (40%): " + decimalFormat.format(getOverallTestGrade()) + "%");
        activityAssignmentBinding.tvQuizGrade.setText("Quiz Grade (20%): " + decimalFormat.format(getOverallQuizGrade()) + "%");
        activityAssignmentBinding.tvHomeworkGrade.setText("Homework Grade (30%): " + decimalFormat.format(getOverallHomeworkGrade())+ "%");
        activityAssignmentBinding.tvLabGrade.setText("Lab Grade (10%): " + decimalFormat.format(getOverallLabGrade())+ "%");

        AssignmentAdapter adapter = new AssignmentAdapter((ArrayList<Assignment>) db.AssignmentDao().getAssignmentsWithCourseId(course_id));
        activityAssignmentBinding.rvAssignment.setAdapter(adapter);
        activityAssignmentBinding.rvAssignment.setLayoutManager(new LinearLayoutManager(this));

        activityAssignmentBinding.assignmentAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AssignmentAddActivity.getIntent(getApplicationContext(), course_id);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context context, int course_id) {
        Intent intent = new Intent(context, AssignmentActivity.class);
        intent.putExtra(COURSE_ID, course_id);
        return intent;
    }

    public double getOverallGrade() {
        double testGrade = getOverallTestGrade();
        double quizGrade = getOverallQuizGrade();
        double labGrade = getOverallLabGrade();
        double homeworkGrade = getOverallHomeworkGrade();

        double overallGrade = (0.4 *testGrade) + (0.2 * quizGrade) + (0.3 *homeworkGrade) + (0.1*labGrade);
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