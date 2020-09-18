package com.example.gradetracker;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.gradetracker.DB.AppDatabase;
import com.example.gradetracker.Model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserDaoTest {
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testInsertUser() throws Exception {
        // insert user into database
        User user = new User("username", "password123", "Tony", "Stark");
        user.setUserID(1);
        db.userDao().insertUser(user);

        // retrieve user from database
        User userInDatabase = db.userDao().getUserById(1);

        // ensure the retrieved user has valid attributes
        assertEquals(userInDatabase.getUserName(), "username");
        assertEquals(userInDatabase.getPassword(), "password123");
        assertEquals(userInDatabase.getFirstName(), "Tony");
        assertEquals(userInDatabase.getLastName(), "Stark");
    }

    @Test
    public void testUpdateUser() throws Exception {
        // insert user into database
        User user = new User("username", "password123", "Tony", "Stark");
        user.setUserID(1);
        db.userDao().insertUser(user);

        // update user attributes
        User userInDatabase = db.userDao().getUserById(1);
        userInDatabase.setUserName("captain_marvel");
        userInDatabase.setPassword("marvelous123");
        userInDatabase.setFirstName("Carol");
        userInDatabase.setLastName("Danvers");
        db.userDao().updateUser(userInDatabase);
        User updatedUserInDatabase = db.userDao().getUserById(1);

        // ensure the updates have persisted
        assertEquals(updatedUserInDatabase.getUserName(), "captain_marvel");
        assertEquals(updatedUserInDatabase.getPassword(), "marvelous123");
        assertEquals(updatedUserInDatabase.getFirstName(), "Carol");
        assertEquals(updatedUserInDatabase.getLastName(), "Danvers");
    }

    @Test
    public void testDeleteUser() throws Exception {
        // insert user into database
        User user = new User("username", "password123", "Tony", "Stark");
        user.setUserID(1);
        db.userDao().insertUser(user);

        //delete user
        db.userDao().deleteUser(user);
        assertNull(db.userDao().getUserById(1));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        // insert users into database
        User user1 = new User("username", "password123", "Tony", "Stark");
        User user2 = new User("captain_marvel", "marvelous123", "Carol", "Danvers");
        user1.setUserID(1);
        user2.setUserID(2);
        db.userDao().insertUser(user1);
        db.userDao().insertUser(user2);

        // retrieve all users
        ArrayList<User> users = (ArrayList<User>) db.userDao().getAllUsers();
        assertEquals(users.size(), 2);

    }

    @Test
    public void testDeleteAllUsers() throws Exception {
        // insert users into database
        User user1 = new User("username", "password123", "Tony", "Stark");
        User user2 = new User("captain_marvel", "marvelous123", "Carol", "Danvers");
        user1.setUserID(1);
        user2.setUserID(2);
        db.userDao().insertUser(user1);
        db.userDao().insertUser(user2);

        // ensure all users have been deleted
        db.userDao().deleteAllUsers();
        assertEquals(db.userDao().getAllUsers().size(), 0);
    }
}

