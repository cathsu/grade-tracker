package com.example.gradetracker;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.Model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "testing";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        // Checking if Course DAO works
        Course course1 = new Course("Drew Clickenbeard",
                "CST 438",
                "Software engineering class",
                LocalDate.now().toString(),
                LocalDate.of(2020, 12, 18).toString(),
                90.0);

        ArrayList<Course> courses = new ArrayList<>();
        db.courseDao().insertCourse(course1);
        Log.d(TAG, "Testing Course class");
        Log.d(TAG, course1.toString());
        Log.d(TAG, course1.getStartDateAsLocalDate().toString());
        Log.d(TAG, course1.getEndDateAsLocalDate().toString());
        courses.add(course1);

        // Checking if UserDao works
        User user = new User("username", "password", "Hermione", "Granger", courses);
        db.userDao().insertUser(user);
        Log.d(TAG, "Inserting user");
        Log.d(TAG, user.toString());
        List<User> users = db.userDao().getAllUsers();
        Log.d(TAG, "Testing ArrayList<Courses> in User class");
        Log.d(TAG, users.get(0).toString());
        Log.d(TAG, users.get(0).getCourses().toString());
        Log.d(TAG, users.get(0).getCourses().get(0).getStartDateAsLocalDate().toString());
        Log.d(TAG, users.get(0).getCourses().get(0).getEndDateAsLocalDate().toString());

        // Delete mock User and Course objects
        db.userDao().deleteAllUsers();
        //Todo: Delete all courses
        Log.d(TAG, "Testing deletion");
        Log.d(TAG, db.userDao().getAllUsers().toString());
    }
}