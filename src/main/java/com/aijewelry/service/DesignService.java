package com.aijewelry.service;

import com.aijewelry.model.Design;
import com.aijewelry.model.DesignUploadRequest;

import java.util.List;

public interface DesignService {
    String saveDesign(DesignUploadRequest designUploadRequest) throws Exception;
    List<Design> getUserDesigns(String userId) throws Exception;
    List<Design> getMarketDesigns() throws Exception;
    void setAddToMarket(String userId, String designId, Boolean addToMarket) throws Exception;

}
