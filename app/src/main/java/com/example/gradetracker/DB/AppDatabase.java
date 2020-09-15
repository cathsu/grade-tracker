package com.example.gradetracker.DB;

import android.content.Context;

import com.example.gradetracker.DB.TypeConverters.ArrayListConverter;
import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.Model.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Assignment.class, Course.class, User.class}, version = 10)
@TypeConverters({ArrayListConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final String DBNAME ="grade_tracker_db";
    private static AppDatabase instance;

    // Todo: Uncomment when AssignmentDao and CourseDao are merged in
    public abstract AssignmentDao AssignmentDao();
//
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
