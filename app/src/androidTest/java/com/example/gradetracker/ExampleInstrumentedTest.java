package com.example.gradetracker;

import android.content.Context;

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

import androidx.annotation.UiThread;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private UserDao userDao;
    private AppDatabase db;
    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .build();
//        userDao = db.userDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    @UiThread
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.gradetracker", appContext.getPackageName());

        Course course1 = new Course(1, "Drew Clickenbeard",
                "CST 438",
                "Software engineering class",
                LocalDate.of(2020, 8, 24).toString(),
                LocalDate.of(2020, 12, 18).toString(),
                90.0);
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course1);

        User user1 = new User("username", "password", "Hermione", "Granger", courses);
        db.userDao().insertUser(user1);


////        assertFalse( signup.isUsernameUnique("username") );
//        Signup signup = new Signup();
//        assertTrue( signup.isUsernameUnique(db.userDao().getUserWithUsername("username")) );
    }
}