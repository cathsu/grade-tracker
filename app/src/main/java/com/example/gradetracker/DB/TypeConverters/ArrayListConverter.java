package com.example.gradetracker.DB.TypeConverters;

import androidx.room.TypeConverter;

import com.example.gradetracker.Model.Course;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


// Citation: https://medium.com/@amit.bhandari/storing-java-objects-other-than-primitive-types-in-room-database-11e45f4f6d22
public class ArrayListConverter {

    @TypeConverter
    public static String fromArrayListCourses(ArrayList<Course> courses) {
        Gson gson = new Gson();
        String json = gson.toJson(courses);
        return json;
    }

    @TypeConverter
    public static ArrayList<Course> fromCourseString(String course) {
        Type courseType = new TypeToken<ArrayList<Course>>() {}.getType();
        return new Gson().fromJson(course, courseType);
    }
}
