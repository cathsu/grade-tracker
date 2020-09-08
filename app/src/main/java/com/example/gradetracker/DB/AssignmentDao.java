package com.example.gradetracker.DB;


import com.example.gradetracker.Model.Assignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;


@Dao
public interface AssignmentDao {

    @Insert
    void insertAssignment(Assignment assignment);

    @Update
    void updateAssignment(Assignment assignment);

    @Delete
    void deleteAssignment(Assignment assignment);

    @Query("SELECT * FROM assignment WHERE assignment_description = :assignDescription")
    Assignment getAssignmentId(String assignDescription);

    @Query("SELECT * FROM assignment WHERE max_points = :maxPoints")
    List<Assignment> getAssignmentMaxPoints(String maxPoints);

    @Query("SELECT * FROM assignment WHERE earned_points = :earnPoints")
    List<Assignment> getAssignmentEarnPoints(int earnPoints);

    @Query("SELECT * FROM assignment WHERE assigned_date = :assignedDate")
    List<Assignment> getAssignmentAssignedDate(String assignedDate);

    @Query("SELECT * FROM assignment WHERE due_date = :dueDate")
    List<Assignment> getAssignmentDueDate(String dueDate);

    @Query("SELECT * FROM assignment WHERE category_name = :categoryName")
    List<Assignment> getAssignmentCategoryName(String categoryName);

    @Query("SELECT * FROM assignment WHERE course_id = :courseId")
    List<Assignment> getAssignmentInCourseId(int courseId);
}

