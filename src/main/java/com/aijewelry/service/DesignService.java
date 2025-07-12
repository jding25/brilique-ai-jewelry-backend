package com.aijewelry.service;

import com.aijewelry.model.Design;

import java.util.List;

public interface DesignService {
    void saveDesign(String userId, Design design);
    List<Design> getUserDesigns(String userId);
}
