package com.aijewelry.dao;

import com.aijewelry.model.Design;

import java.util.List;

public interface DesignDao {
    void saveDesign(Design design);
    List<Design> getDesignsByUser(String userId) throws Exception;
    List<Design> getMarketDesigns();
    void setAddToMarket(String userId, String designId, Boolean addToMarket) throws Exception;
    List<Design> getOnMarketDesignByUser(String userId) throws Exception;
    Design getDesign(String userId, String designId) throws Exception;
}
