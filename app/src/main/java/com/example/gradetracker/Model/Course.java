package com.example.gradetracker.Model;

import java.time.LocalDate;
import java.time.LocalTime;


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
    private LocalDate startDate;
    @ColumnInfo(name = "end_date")
    private LocalDate endDate;
    @ColumnInfo(name = "course_time")
    private LocalTime courseTime;
    @ColumnInfo(name = "grade")
    private Double grade;

    public Course(String instructor, String courseName, String description, LocalDate startDate, LocalDate endDate, LocalTime courseTime, Double grade) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getCourseTime() {
        return courseTime;
    }

    public Double getGrade() {
        return grade;
    }
}
