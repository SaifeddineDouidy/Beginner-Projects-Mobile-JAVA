package com.example.login_signup.services;


import android.content.Context;

import com.example.login_signup.beans.User;
import com.example.login_signup.utils.MyDatabaseHelper;

import java.util.List;

public class UserService {
    private MyDatabaseHelper dbHelper;

    public UserService(Context context) {
        dbHelper = new MyDatabaseHelper(context);
    }

    // Register a new user
    public boolean registerUser(String fullname, String email, String password) {
        // You might want to add validation logic here (e.g., check for existing email)
        return dbHelper.registerUser(fullname, email, password);
    }

    // Update user information
    public boolean updateUser(int id, String fullname, String email, String password) {
        // Implement any necessary validation or logic here
        return dbHelper.updateUser(id, fullname, email, password);
    }

    public boolean deleteUser(User user) {
        // Implement any necessary validation or logic here
        return dbHelper.deleteUser(user);
    }

    // Retrieve a user by ID
    public User getUserById(int id) {
        return dbHelper.getUserById(id);
    }

    public List<User> getAllUsers() {
        return dbHelper.getAllUsers();
    }

    // Other user-related methods can be added here (e.g., login, delete)
}

