package com.example.gradetracker.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.gradetracker.Model.Assignment;
import com.example.gradetracker.Model.Course;
import com.example.gradetracker.Model.User;

@Database(entities = {Assignment.class, Course.class, User.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class GradeTrackerDatabase extends RoomDatabase {

    private static GradeTrackerDatabase instance;

    // Todo: Uncomment when AssignmentDao and CourseDao are merged in
//    public abstract AssignmentDao AssignmentDao();
//
//    public abstract CourseDao CourseDao();

    public abstract UserDao UserDao();

    public static synchronized GradeTrackerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    GradeTrackerDatabase.class, "grade_tracker_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
