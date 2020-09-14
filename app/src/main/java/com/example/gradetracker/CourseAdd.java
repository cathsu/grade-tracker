package com.example.gradetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.Model.Course;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class CourseAdd  extends AppCompatActivity {

    private EditText mName;
    private EditText mInstructor;
    private EditText mStartDate;
    private EditText mEndDate;
    private EditText mDescription;

    private AppDatabase db;

    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);

        mName = findViewById(R.id.tvCourseName);
        mInstructor = findViewById(R.id.tvInstructor);
        mStartDate = findViewById(R.id.tvStartDate);
        mEndDate = findViewById(R.id.tvEndDate);
        mDescription = findViewById(R.id.tvDescription);

        mAddButton = findViewById(R.id.button);
        db = AppDatabase.getInstance(getApplicationContext());


        final String name = mName.getText().toString();
        final String instructor = mInstructor.getText().toString();
        final String startDate = mStartDate.getText().toString();
        final String endDate = mEndDate.getText().toString();
        final String description = mDescription.getText().toString();


        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course newCourse = new Course(instructor, name, description, startDate, endDate, null);
                List<Course> allCourses = db.courseDao().getAllCourses();
                boolean check = true;

                for (Course c : allCourses) {
                    if (c.getCourseName().equals(newCourse.getCourseName())) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Course name has already been taken", Toast.LENGTH_LONG);
                        toast.show();
                        check = false;
                        break;
                    }
                }
                if (check) {
                    db.courseDao().insertCourse(newCourse);
                    Toast toast = Toast.makeText(getApplicationContext(), "Course has been added", Toast.LENGTH_LONG);
                    toast.show();

                }
            }
        });
    }
}
