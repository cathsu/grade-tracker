package com.example.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.gradetracker.Adapters.CourseAdapter;
import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.Model.User;
import com.example.gradetracker.databinding.ActivityCoursesBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class CoursesActivity extends AppCompatActivity {
    private static String COURSE_ID = "course_id";
    private ActivityCoursesBinding activityCoursesBinding;
    private AppDatabase db;
    private User user;
    private CourseAdapter courseAdapter;
    List<Course> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = AppDatabase.getInstance(getApplicationContext());
        activityCoursesBinding = ActivityCoursesBinding.inflate(getLayoutInflater());
        View view = activityCoursesBinding.getRoot();
        setContentView(view);
        String username = getIntent().getStringExtra("username");
        assert username != null;
        Log.d("CourseActivity", username);
        user = db.userDao().getUserWithUsername(username);
        courses = db.courseDao().getCoursesByUserId(user.getUserID());
        if (courses.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "No courses to display. Feel free to add some!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            courseAdapter = new CourseAdapter(courses);
            activityCoursesBinding.rvCourses.setAdapter(courseAdapter);
            activityCoursesBinding.rvCourses.setLayoutManager(new LinearLayoutManager(this));
            courseAdapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
                @Override
                public void onEditClick(int p) {
                    editItem(p);
                    courses.set(p, courses.get(p));
                    courseAdapter.notifyDataSetChanged();
                }

                @Override
                public void onDeleteClick(int p) {
                    deleteItem(p);
                }

                @Override
                public void onViewClick(int p) {
                    Intent intent = new Intent(getApplicationContext(), AssignmentActivity.class);
                    intent.putExtra(COURSE_ID, courses.get(p).getCourseID());
                    startActivity(intent);
                }
            });
        }

        activityCoursesBinding.fabAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewCourseActivity.class);
                intent.putExtra("username", user.getUserName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // user can only sign out by clicking log out button
    }

    private void deleteItem(int position) {
        db.courseDao().deleteCourse(courses.get(position));
        courses.remove(position);
        courseAdapter.notifyItemRemoved(position);
        courseAdapter.notifyItemRangeChanged(position, courses.size());
    }

    private void editItem(int position) {
        Intent intent = new Intent(getApplicationContext(), EditCourseActivity.class);
        intent.putExtra("courseId", courses.get(position).getCourseID());
        intent.putExtra("username", user.getUserName());
        startActivity(intent);
    }
}