package com.aijewelry.service;

import com.aijewelry.dao.DesignDao;
import com.aijewelry.dao.DesignDaoImpl;
import com.aijewelry.model.Design;

import java.util.List;
import java.util.UUID;

public class DesignServiceImpl implements DesignService{
    private final DesignDao dao;

    public DesignServiceImpl(DesignDao dao) {
        this.dao = dao;
    }

    @Override
    public void saveDesign(String userId, Design design) {
        design.setId(UUID.randomUUID().toString());
        design.setUserId(userId);
        dao.saveDesign(design);
    }

    @Override
    public List<Design> getUserDesigns(String userId) {
        return dao.getDesignsByUser(userId);
    }
}
