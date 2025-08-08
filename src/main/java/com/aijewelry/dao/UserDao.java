package com.aijewelry.dao;

import com.aijewelry.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);
    User getUser(String email);
    List<User> retrieveAllUsers();
}
