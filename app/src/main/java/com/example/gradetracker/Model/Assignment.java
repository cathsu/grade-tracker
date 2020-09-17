package com.example.gradetracker.Model;

import android.os.Build;

import java.time.LocalDate;

import androidx.annotation.RequiresApi;
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
    @ColumnInfo(name="assignment_name")
    private String name;
    @ColumnInfo(name = "assignment_description")
    private String assignmentDescription;
    @ColumnInfo(name = "max_points")
    private Integer maxPoints;
    @ColumnInfo(name = "earned_points")
    private Integer earnedPoints;
    @ColumnInfo(name = "assigned_date")
    private String assignedDate;
    @ColumnInfo(name = "due_date")
    private String dueDate;
    @ColumnInfo(name = "category_name")
    private String categoryName;
    @ColumnInfo(name = "course_id")
    private Integer courseID;

    public Assignment(String name, String assignmentDescription, Integer maxPoints, Integer earnedPoints, String assignedDate, String dueDate, String categoryName, Integer courseID) {
        this.name = name;
        this.assignmentDescription = assignmentDescription;
        this.maxPoints = maxPoints;
        this.earnedPoints = earnedPoints;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.categoryName = categoryName;
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public Integer getEarnedPoints() {
        return earnedPoints;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }

    public void setEarnedPoints(Integer earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Double getPercentageGrade() {
        double gradeValue = (((double) earnedPoints) / maxPoints)*100 ;
        return gradeValue;
    }
    public String getLetterGrade() {
        double gradeValue = ((double) earnedPoints) / maxPoints;
        if (gradeValue < 0.9) {
            return "A";
        }
        else if (gradeValue < 0.8) {
            return "B";
        }
        else if (gradeValue < 0.7) {
            return "C";
        }
        else if (gradeValue < 0.6) {
            return "D";
        }
        else {
            return "F";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate getAssignedDateAsLocalDate() {
        return LocalDate.parse(assignedDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate getDueDateAsLocalDate() {
        return LocalDate.parse(dueDate);
    }
}