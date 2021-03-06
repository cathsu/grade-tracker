package com.example.gradetracker.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.gradetracker.DB.TypeConverters.ArrayListConverter;
import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.Model.User;

@Database(entities = {Assignment.class, Course.class, User.class}, version = 12)
@TypeConverters({ArrayListConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final String DBNAME ="grade_tracker_db";
    private static AppDatabase instance;

    public abstract AssignmentDao AssignmentDao();

    public abstract CourseDao courseDao();

    public abstract UserDao userDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DBNAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
