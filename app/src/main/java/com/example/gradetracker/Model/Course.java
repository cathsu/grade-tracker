package com.example.gradetracker.Model;

import android.os.Build;

import java.time.LocalDate;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course")
public class Course {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "course_id")
    private Integer courseID;
    @ColumnInfo(name = "user_id")
    private Integer userID;
    @ColumnInfo(name = "instructor")
    private String instructor;
    @ColumnInfo(name = "course_name")
    private String courseName;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "start_date")
    private String startDate;
    @ColumnInfo(name = "end_date")
    private String endDate;
    @ColumnInfo(name = "grade")
    private Double grade;

    public Course(Integer userID, String instructor, String courseName, String description, String startDate, String endDate,Double grade) {
        this.userID = userID;
        this.instructor = instructor;
        this.courseName = courseName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Double getGrade() {
        return grade;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate getStartDateAsLocalDate() {
        return LocalDate.parse(startDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate getEndDateAsLocalDate() {
        return LocalDate.parse(endDate);
    }

    @Override
    public String toString() {
        return "Course{" +
                "UserID=" + userID +
                ", courseID=" + courseID +
                ", instructor='" + instructor + '\'' +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate.toString() +
                ", endDate=" + endDate.toString() +
                ", grade=" + grade +
                '}';
    }
}
