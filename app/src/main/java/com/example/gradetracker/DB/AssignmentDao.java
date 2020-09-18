package com.example.gradetracker.DB;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gradetracker.Model.Assignment;

import java.util.List;


@Dao
public interface AssignmentDao {

    /**
     * Insert an assignment into the database
     * @param assignment
     */
    @Insert
    void insertAssignment(Assignment assignment);

    /**
     * Update attributes of an assignment already in the database
     * @param assignment
     */
    @Update
    void updateAssignment(Assignment assignment);

    /**
     * Delete an assignment from the database
     * @param assignment
     */
    @Delete
    void deleteAssignment(Assignment assignment);

    /**
     * Delete all assignments from the database
     */
    @Query("DELETE FROM assignment")
    void deleteAllAssignment();

    /**
     * Retrieve all assignments from the database
     * @return a list of all the assignments from the database
     */
    @Query("SELECT * FROM assignment")
    List<Assignment> getAllAssignments();

    /**
     * Retrieve all assignments with a specific course id and category
     * @param courseId
     * @param categoryName
     * @return a list of assignments with the specified course id and category
     */

    @Query("SELECT * FROM assignment WHERE course_id = :courseId and category_name = :categoryName")
    List<Assignment> getAssignmentsWithCourseIdAndCategory(int courseId, String categoryName);

    /**
     * Retrieve all assignments with a specific course id.
     * @param courseId
     * @return a list of assignments with the specified course id; the list is ordered by the category
     */
    @Query("SELECT * FROM assignment WHERE course_id = :courseId ORDER by category_name")
    List<Assignment> getAssignmentsWithCourseId(int courseId);

    /**
     * Retrieve assignment with a specific assignment id
     * @param assignmentId
     * @return an assignment with the specified assignment id
     */
    @Query("SELECT * FROM assignment WHERE assignmentID = :assignmentId")
    Assignment getAssignmentWithId(int assignmentId);

}

