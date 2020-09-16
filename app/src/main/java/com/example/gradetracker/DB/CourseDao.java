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
    @Insert
    void insertCourse(Course course);
    @Update
    void updateCourse(Course course);
    @Delete
    void deleteCourse(Course course);

    @Query("DELETE from course")
    void deleteAllCourses();

    @Query("SELECT * FROM course")
    List<Course> getAllCourses();

    @Query("SELECT * FROM course WHERE course_id = :courseId")
    List<Course> getCourseId(int courseId);

    @Query("SELECT * FROM course WHERE instructor = :instructor")
    List<Course> getCourseInstructor(String instructor);

    @Query("SELECT * FROM course WHERE course_name = :courseName")
    List<Course> getCourseCourseName(String courseName);

    @Query("SELECT * FROM course WHERE description = :description")
    List<Course> getCourseDescription(String description);

    @Query("SELECT * FROM course WHERE start_date = :startDate")
    List<Course> getCourseStartDate(String startDate);

    @Query("SELECT * FROM course WHERE end_date = :endDate")
    List<Course> getCourseEndDate(String endDate);

    @Query("SELECT * FROM course WHERE grade = :grade")
    List<Course> getCourseGrade(double grade);

    @Query("SELECT course_id FROM course where course_name = :courseName")
    Integer getCourseIdFromName(String courseName);

    @Query("SELECT * FROM course WHERE user_id = :userId")
    List<Course> getCoursesByUserId(Integer userId);
}
