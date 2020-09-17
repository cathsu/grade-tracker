package com.example.gradetracker.DB;

import com.example.gradetracker.Model.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM users")
    void deleteAllUsers();

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM users where username LIKE :search")
    User getUserWithUsername(String search);

    @Query("SELECT username from users where userID=:userId")
    String getUserById(Integer userId);
}
