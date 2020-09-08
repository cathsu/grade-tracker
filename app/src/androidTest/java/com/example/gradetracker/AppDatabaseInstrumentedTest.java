package com.example.gradetracker;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.UserDao;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.Model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class AppDatabaseInstrumentedTest {
    private UserDao userDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDao = db.userDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void createUser() throws Exception {

        Course course1 = new Course("Drew Clickenbeard",
                "CST 438",
                "Software engineering class",
                LocalDate.of(2020, 8, 24).toString(),
                LocalDate.of(2020, 12, 18).toString(),
                90.0);
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course1);
        User user1 = new User("username", "password", "Hermione", "Granger", courses);
//        User user2 = new User("ron", "password2", "Ron", "Weasley", courses);
        userDao.insertUser(user1);

        User foundUser = userDao.getUserWithUsername("username");
//        assertThat(foundUser.getUserName(), equalTo("username"));
        assertEquals(foundUser.getUserName(), "username");
        assertEquals(foundUser.getPassword(), "password");
        assertEquals(foundUser.getFirstName(), "Hermione");
        assertEquals(foundUser.getLastName(), "Granger");
    }
}
