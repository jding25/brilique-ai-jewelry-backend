package com.aijewelry.service;

import com.aijewelry.model.User;
import com.aijewelry.model.UserUploadRequest;

import java.util.List;

public interface UserService {
    void saveUser(UserUploadRequest userUploadRequest) throws Exception;
    User getUser(String email);
    List<User> retrieveAllUsers();
}
