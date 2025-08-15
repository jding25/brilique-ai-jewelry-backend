package com.aijewelry.service;

import com.aijewelry.dao.DynamoDbDesignDao;
import com.aijewelry.dao.DynamoDbUserDao;
import com.aijewelry.model.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
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
        user.setLikedDesigns(new HashSet<>());
        user.setOrders(new ArrayList<>());

        userDao.saveUser(user);
    }

    @Override
    public User getUser(String email) {
        return userDao.getUser(email);
    }

    @Override
    public List<User> retrieveAllUsers() {return userDao.retrieveAllUsers();}

    @Override
    public void likeDesign(String userId, String designUserId, String designId) throws Exception {
        String likeKey = DesignKey.encode(designUserId, designId);


    }

    @Override
    public void unlikeDesign(String userId, String designUserId, String designId) throws Exception {

    }

    @Override
    public void retrieveLikedDesigns(String userId) throws Exception {

    }


}
