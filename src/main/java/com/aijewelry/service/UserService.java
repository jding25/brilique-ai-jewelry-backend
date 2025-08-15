package com.aijewelry.service;

import com.aijewelry.model.User;
import com.aijewelry.model.UserUploadRequest;

import java.util.List;

public interface UserService {
    void saveUser(UserUploadRequest userUploadRequest) throws Exception;
    User getUser(String email);
    List<User> retrieveAllUsers();
    void likeDesign(String userId, String designUserId, String designId) throws Exception;
    void unlikeDesign(String userId, String designUserId, String designId) throws Exception;
    void retrieveLikedDesigns(String userId) throws Exception;
}
