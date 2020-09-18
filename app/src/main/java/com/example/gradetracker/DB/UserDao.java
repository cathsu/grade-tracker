package com.example.gradetracker.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gradetracker.Model.User;

import java.util.List;

@Dao
public interface UserDao {

    /**
     * Insert an user into the database
     * @param user
     */
    @Insert
    void insertUser(User user);

    /**
     * Update attributes of an user
     * @param user
     */
    @Update
    void updateUser(User user);

    /**
     * Delete a user from the database
     * @param user
     */
    @Delete
    void deleteUser(User user);

    /**
     * Delete all users from the database
     */
    @Query("DELETE FROM users")
    void deleteAllUsers();

    /**
     * Retrieve all users from the database
     * @return a list of all the users in the database
     */
    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    /**
     * Retrieve the user with the specified username
     * @param search
     * @return the user with the specified username
     */
    @Query("SELECT * FROM users where username LIKE :search")
    User getUserWithUsername(String search);

    /**
     * Retrieve the user with the specified user id
     * @param userId
     * @return the user with the specified user id
     */
    @Query("SELECT username from users where userID=:userId")
    String getUserById(Integer userId);
}
