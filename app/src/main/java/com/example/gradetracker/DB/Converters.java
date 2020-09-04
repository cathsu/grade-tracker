package com.example.gradetracker.DB;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.gradetracker.Model.Course;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static String toDateString(LocalDate date) {
        return date == null ? null : date.toString();
    }

    @TypeConverter
    public static String toTimeString(LocalTime time) {
        return time == null ? null : time.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalDate toDate(String dateString) {
        return dateString  == null ? null : LocalDate.parse(dateString);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalTime toTime(String timeString) {
        return timeString  == null ? null : LocalTime.parse(timeString);
    }

//    @TypeConverter
//    public static ArrayList<Course> fromString(String course) {
//        Type courseType = new TypeToken<ArrayList<Course>>() {}.getType();
//        return new Gson().fromJson(course, courseType);
//    }
}
