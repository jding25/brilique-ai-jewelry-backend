package com.aijewelry.dao;

import com.aijewelry.model.User;

public interface UserDao {
    void saveUser(User user);
    User getUser(String email);
}
