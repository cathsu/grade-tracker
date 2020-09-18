package com.example.gradetracker;

import android.content.Context;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.DB.CourseDao;
import com.example.gradetracker.Model.Course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class CourseDAOTest {
    private AppDatabase db;
    private CourseDao courseDao;
    private Course testCourse = new Course(1, "Spongebob Squarepants", "Intro to Jellyfishing", "Class that teaches fundamentals of jellyfishing.", "1/1/2020", "12/1/2020", null);

    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        courseDao = db.courseDao();
        courseDao.insertCourse(testCourse);
    }

    @After
    public void closeDB() {
        db.close();
    }

    @Test
    public void insertCourse() {
        Course course = new Course(2, "Harry", "Intro to Dementor Hunting", "Class that teaches fundamentals of magic.", "1/1/2020", "12/1/2020", null);
        course.setCourseID(2);
        courseDao.insertCourse(course);
        Course sameCourse = courseDao.getCourseId(2).get(0);
        assertEquals(course.getUserID(), sameCourse.getUserID());
        assertEquals(course.getInstructor(), sameCourse.getInstructor());
        assertEquals(course.getCourseName(), sameCourse.getCourseName());
        assertEquals(course.getDescription(), sameCourse.getDescription());
        assertEquals(course.getStartDate(), sameCourse.getStartDate());
        assertEquals(course.getEndDate(), sameCourse.getEndDate());
    }

    @Test
    public void deleteCourse() {
        Course course = new Course(2, "Harry", "Intro to Dementor Hunting", "Class that teaches fundamentals of magic.", "1/1/2020", "12/1/2020", null);
        courseDao.deleteCourse(course);
        Course deleteCourse = courseDao.getCourseById(2);
        assertNull(deleteCourse);
    }

    @Test
    public void testGetCourseById() {
        Course tempCourse = new Course(1, "Spongebob Squarepants", "Intro to Jellyfishing", "Class that teaches fundamentals of jellyfishing.", "1/1/2020", "12/1/2020", null);
        Course course = courseDao.getCourseById(1);
        assertEquals(course.getUserID(), tempCourse.getUserID());
        assertEquals(course.getInstructor(), tempCourse.getInstructor());
        assertEquals(course.getCourseName(), tempCourse.getCourseName());
        assertEquals(course.getDescription(), tempCourse.getDescription());
        assertEquals(course.getStartDate(), tempCourse.getStartDate());
        assertEquals(course.getEndDate(), tempCourse.getEndDate());
    }

    @Test
    public void testGetCourseByUserId() {
        Course tempCourse = new Course(1, "Spongebob Squarepants", "Intro to Jellyfishing", "Class that teaches fundamentals of jellyfishing.", "1/1/2020", "12/1/2020", null);
        Course course = courseDao.getCoursesByUserId(1).get(0);
        assertEquals(course.getUserID(), tempCourse.getUserID());
        assertEquals(course.getInstructor(), tempCourse.getInstructor());
        assertEquals(course.getCourseName(), tempCourse.getCourseName());
        assertEquals(course.getDescription(), tempCourse.getDescription());
        assertEquals(course.getStartDate(), tempCourse.getStartDate());
        assertEquals(course.getEndDate(), tempCourse.getEndDate());
    }
}
