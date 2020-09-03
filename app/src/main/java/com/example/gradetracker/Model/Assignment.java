package com.example.gradetracker.Model;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Course.class,
                parentColumns = "course_id",
                childColumns = "course_id"
        )
})

public class Assignment {
    @PrimaryKey(autoGenerate = true)
    private Integer assignmentID;
    @ColumnInfo(name = "assignment_description")
    private String assignmentDescription;
    @ColumnInfo(name = "max_points")
    private String maxPoints;
    @ColumnInfo(name = "earned_points")
    private Integer earnedPoints;
    @ColumnInfo(name = "assigned_date")
    private Date assignedDate;
    @ColumnInfo(name = "due_date")
    private Date dueDate;
    @ColumnInfo(name = "category_name")
    private String categoryName;
    @ColumnInfo(name = "course_id")
    private Integer courseID;

    public Assignment(String assignmentDescription, String maxPoints, Integer earnedPoints, Date assignedDate, Date dueDate, String categoryName, Integer courseID) {
        this.assignmentDescription = assignmentDescription;
        this.maxPoints = maxPoints;
        this.earnedPoints = earnedPoints;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.categoryName = categoryName;
        this.courseID = courseID;
    }

    public void setAssignmentID(Integer assignmentID) {
        this.assignmentID = assignmentID;
    }

    public Integer getAssignmentID() {
        return assignmentID;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public String getMaxPoints() {
        return maxPoints;
    }

    public Integer getEarnedPoints() {
        return earnedPoints;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getCourseID() {
        return courseID;
    }
}
