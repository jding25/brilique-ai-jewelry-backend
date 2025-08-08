package com.aijewelry.service;

import com.aijewelry.dao.DynamoDbDesignDao;
import com.aijewelry.dao.DynamoDbUserDao;
import com.aijewelry.model.Design;
import com.aijewelry.model.DesignUploadRequest;
import com.aijewelry.model.User;
import com.aijewelry.model.UserUploadRequest;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService{
    private final DynamoDbUserDao userDao = new DynamoDbUserDao();


    @Override
    public void saveUser(UserUploadRequest userUploadRequest) throws Exception {
        User user = new User();
        user.setEmail(userUploadRequest.email);
        user.setName(userUploadRequest.name);
        user.setProfilePicUrl(userUploadRequest.profilePicUrl);

        userDao.saveUser(user);
    }

    @Override
    public User getUser(String email) {
        return userDao.getUser(email);
    }

    @Override
    public List<User> retrieveAllUsers() {return userDao.retrieveAllUsers();};
}
