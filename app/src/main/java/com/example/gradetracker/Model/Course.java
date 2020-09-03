package com.example.gradetracker.Model;

import java.util.Date;

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
    private Date startDate;
    @ColumnInfo(name = "end_date")
    private Date endDate;
    @ColumnInfo(name = "course_time")
    private Date courseTime;
    @ColumnInfo(name = "grade")
    private Double grade;

    public Course(String instructor, String courseName, String description, Date startDate, Date endDate, Date courseTime, Double grade) {
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getCourseTime() {
        return courseTime;
    }

    public Double getGrade() {
        return grade;
    }
}
