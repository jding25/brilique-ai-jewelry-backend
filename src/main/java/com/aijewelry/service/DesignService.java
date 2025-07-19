package com.aijewelry.service;

import com.aijewelry.model.Design;
import com.aijewelry.model.DesignUploadRequest;

import java.util.List;

public interface DesignService {
    void saveDesign(DesignUploadRequest designUploadRequest) throws Exception;
    List<Design> getUserDesigns(String userId) throws Exception;
}
