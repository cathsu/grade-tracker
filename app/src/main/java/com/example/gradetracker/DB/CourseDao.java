package com.example.gradetracker.DB;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.gradetracker.Model.Course;

@Dao
public interface CourseDao {
    @Insert
    void insertCourse(Course course);
}
