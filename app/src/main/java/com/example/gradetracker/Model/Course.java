package com.example.gradetracker.Model;

import java.util.Calendar;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course")
public class Course {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "course_id")
    private Integer courseID;
    @ColumnInfo(name = "instructor")
    private String instructor;
    @ColumnInfo(name = "course_name")
    private String courseName;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "start_date")
    private Calendar startDate;
    @ColumnInfo(name = "end_date")
    private Calendar endDate;
    @ColumnInfo(name = "course_time")
    private Calendar courseTime;
    @ColumnInfo(name = "grade")
    private Double grade;

    public Course(String instructor, String courseName, String description, Calendar startDate, Calendar endDate, Calendar courseTime, Double grade) {
        this.instructor = instructor;
        this.courseName = courseName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseTime = courseTime;
        this.grade = grade;
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

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public Calendar getCourseTime() {
        return courseTime;
    }

    public Double getGrade() {
        return grade;
    }
}
