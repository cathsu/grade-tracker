package com.example.gradetracker.DB;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import com.example.gradetracker.Model.Course;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static String fromLocalDate (LocalDate date) {
        return date == null ? null : date.toString();
    }

    @TypeConverter
    public static String fromLocalTime(LocalTime time) {
        return time == null ? null : time.toString();
    }

    @TypeConverter
    public static String fromCourseArrayList(ArrayList<Course> courses) {
        Gson gson = new Gson();
        String json = gson.toJson(courses);
        return json;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalDate fromDateString(String dateString) {
        return dateString  == null ? null : LocalDate.parse(dateString);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalTime fromTimeString(String timeString) {
        return timeString  == null ? null : LocalTime.parse(timeString);
    }

    @TypeConverter
    public static ArrayList<Course> fromCourseString(String course) {
        Type courseType = new TypeToken<ArrayList<Course>>() {}.getType();
        return new Gson().fromJson(course, courseType);
    }
}
