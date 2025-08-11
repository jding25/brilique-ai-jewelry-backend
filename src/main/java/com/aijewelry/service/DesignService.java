package com.aijewelry.service;

import com.aijewelry.model.Design;
import com.aijewelry.model.DesignUploadRequest;

import java.util.List;

public interface DesignService {
    Design saveDesign(DesignUploadRequest designUploadRequest) throws Exception;
    List<Design> getUserDesigns(String userId) throws Exception;
    List<Design> getMarketDesigns() throws Exception;
    void setAddToMarket(String userId, String designId, Boolean addToMarket) throws Exception;
    List<Design> getOnMarketDesignByUser(String userId) throws Exception;
    Design getDesign(String userId, String designId) throws Exception;

}
