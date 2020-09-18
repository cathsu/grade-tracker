package com.example.gradetracker;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.Model.Course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AssignmentDaoTest {
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testInsertAssignment() throws Exception {
        // insert course and assignment into database
        Course course = new Course(1, "Drew Clickenbeard", "CST 438", "Intro to software engineering", "08-24-2020", "12-24-2020", 90.3);
        db.courseDao().insertCourse(course);
        Assignment assignment = new Assignment("Exam 1", "This is the first exam", 100, 84, "09-20-2020", "09-20-2020", "Test", course.getCourseID());
        db.AssignmentDao().insertAssignment(assignment);
        assignment.setAssignmentID(1);

        // retrieve assignment from database
        Assignment dbAssignment = db.AssignmentDao().getAssignmentWithId(1);

        // ensure the retrieved assignment has valid attributes
        assertEquals(dbAssignment.getName(), "Exam 1");
        assertEquals(dbAssignment.getAssignmentDescription(), "This is the first exam");
        assertEquals(dbAssignment.getMaxPoints(), new Integer(100));
        assertEquals(dbAssignment.getEarnedPoints(), new Integer(84));
        assertEquals(dbAssignment.getAssignedDate(), "09-20-2020");
        assertEquals(dbAssignment.getDueDate(), "09-20-2020");
        assertEquals(dbAssignment.getCategoryName(), "Test");
    }

    @Test
    public void testUpdateAssignment() throws Exception {
        // insert course and assignment into database
        Course course = new Course(1, "Drew Clickenbeard", "CST 438", "Intro to software engineering", "08-24-2020", "12-24-2020", 90.3);
        db.courseDao().insertCourse(course);
        Assignment assignment = new Assignment("Exam 1", "This is the first exam", 100, 84, "09-20-2020", "09-20-2020", "Test", course.getCourseID());
        db.AssignmentDao().insertAssignment(assignment);
        assignment.setAssignmentID(1);

        // retrieve assignment from database
        Assignment dbAssignment = db.AssignmentDao().getAssignmentWithId(1);

        // update the data and check to make sure updates persisted
        dbAssignment.setName("Software Engineering Exam");
        dbAssignment.setEarnedPoints(88);
        db.AssignmentDao().updateAssignment(dbAssignment);
        assertEquals(dbAssignment.getName(), "Software Engineering Exam");
        assertEquals(dbAssignment.getEarnedPoints(), new Integer(88));
    }

    @Test
    public void testDeleteAssignment() throws Exception {
        // insert course and assignment into database
        Course course = new Course(1, "Drew Clickenbeard", "CST 438", "Intro to software engineering", "08-24-2020", "12-24-2020", 90.3);
        db.courseDao().insertCourse(course);
        Assignment assignment = new Assignment("Exam 1", "This is the first exam", 100, 84, "09-20-2020", "09-20-2020", "Test", course.getCourseID());
        db.AssignmentDao().insertAssignment(assignment);
        assignment.setAssignmentID(1);

        // retrieve assignment from database
        Assignment dbAssignment = db.AssignmentDao().getAssignmentWithId(1);

        // delete assignment and check if assignment has been deleted
        db.AssignmentDao().deleteAssignment(dbAssignment);
        assertTrue(db.AssignmentDao().getAssignmentWithId(1) ==  null);
    }

    @Test
    public void testGetAllAssignments() throws Exception {
        // insert course and assignments into database
        Course course = new Course(1, "Drew Clickenbeard", "CST 438", "Intro to software engineering", "08-24-2020", "12-24-2020", 90.3);
        db.courseDao().insertCourse(course);
        Assignment assignment1 = new Assignment("Exam 1", "This is the first exam", 100, 84, "09-20-2020", "09-20-2020", "Test", course.getCourseID());
        Assignment assignment2 = new Assignment("Exam 2", "This is the second exam", 100, 94, "10-20-2020", "10-20-2020", "Test", course.getCourseID());
        db.AssignmentDao().insertAssignment(assignment1);
        db.AssignmentDao().insertAssignment(assignment2);

        // check if all assignments have been retrieved
        ArrayList<Assignment> allAssignments = (ArrayList<Assignment>) db.AssignmentDao().getAllAssignments();
        assertEquals(allAssignments.size(), 2);
        assertEquals(allAssignments.get(0).getName(), "Exam 1");
        assertEquals(allAssignments.get(1).getName(), "Exam 2");
    }

    @Test
    public void testDeleteAllAssignments() throws Exception {
        // insert course and assignments into database
        Course course = new Course(1, "Drew Clickenbeard", "CST 438", "Intro to software engineering", "08-24-2020", "12-24-2020", 90.3);
        db.courseDao().insertCourse(course);
        Assignment assignment1 = new Assignment("Exam 1", "This is the first exam", 100, 84, "09-20-2020", "09-20-2020", "Test", course.getCourseID());
        Assignment assignment2 = new Assignment("Exam 2", "This is the second exam", 100, 94, "10-20-2020", "10-20-2020", "Test", course.getCourseID());
        db.AssignmentDao().insertAssignment(assignment1);
        db.AssignmentDao().insertAssignment(assignment2);

        // check if all assignments have been retrieved
        ArrayList<Assignment> allAssignments = (ArrayList<Assignment>) db.AssignmentDao().getAllAssignments();
        assertEquals(allAssignments.get(0).getName(), "Exam 1");
        assertEquals(allAssignments.get(1).getName(), "Exam 2");

        // check if all assignments have been deleted
        db.AssignmentDao().deleteAllAssignment();
        assertEquals(db.AssignmentDao().getAllAssignments().size(), 0);
    }
}



