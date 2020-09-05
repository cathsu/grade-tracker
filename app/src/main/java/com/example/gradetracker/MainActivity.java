package com.example.gradetracker;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.DB.UserDao;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.Model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        Course course1 = new Course("Drew Clickenbeard",
                "CST 438",
                "Software engineering class",
                LocalDate.now().toString(),
                LocalDate.of(2020, 12, 18).toString(),
                90.0);

        ArrayList<Course> courses = new ArrayList<>();
        db.courseDao().insertCourse(course1);
        Log.d("users", "Course: " + course1.toString());
        courses.add(course1);
        User user = new User("username", "password", "Hermione", "Granger", courses);
        db.userDao().insertUser(user);
        List<User> users = db.userDao().getAllUsers();
        Log.d("users", "Courses in User class: " + users.get(0).getCourses());
    }
}