package com.aijewelry.dao;

import com.aijewelry.model.Design;

import java.util.List;

public interface DesignDao {
    void saveDesign(Design design);
    List<Design> getDesignsByUser(String userId) throws Exception;
}
