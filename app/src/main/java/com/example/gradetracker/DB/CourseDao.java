package com.example.gradetracker.DB;

import com.example.gradetracker.Model.Course;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CourseDao {
    /**
     * This method inserts courses into the database.
     * @param course
     */
    @Insert
    void insertCourse(Course course);

    /**
     * This method updates a course already in the database.
     * @param course
     */
    @Update
    void updateCourse(Course course);

    /**
     * This method deletes a course in the database.
     * @param course
     */
    @Delete
    void deleteCourse(Course course);

    /**
     * This method retrieves courses by the Course's Id.
     * @param courseId
     * @return
     */
    @Query("SELECT * FROM course WHERE course_id = :courseId")
    List<Course> getCourseId(int courseId);

    /**
     * This method gets every course that matches with the user's Id.
     * @param userId
     * @return
     */
    @Query("SELECT * FROM course WHERE user_id = :userId")
    List<Course> getCoursesByUserId(Integer userId);

    /**
     * This method gets every course by the Course's Id.
     * @param courseId
     * @return
     */
    @Query("SELECT * FROM course WHERE course_id = :courseId")
    Course getCourseById(Integer courseId);
}
