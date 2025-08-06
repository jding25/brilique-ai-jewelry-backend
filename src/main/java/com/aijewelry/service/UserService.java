package com.aijewelry.service;

import com.aijewelry.model.User;
import com.aijewelry.model.UserUploadRequest;

public interface UserService {
    void saveUser(UserUploadRequest userUploadRequest) throws Exception;
    User getUser(String email);
}
