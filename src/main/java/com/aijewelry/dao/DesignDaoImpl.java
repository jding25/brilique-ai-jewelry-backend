package com.aijewelry.dao;

import com.aijewelry.model.Design;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DesignDaoImpl implements DesignDao{
    private final List<Design> store = new ArrayList<>();

    @Override
    public void saveDesign(Design design) {
        store.add(design);

    }

    @Override
    public List<Design> getDesignsByUser(String userId) {
        return store.stream()
                .filter(d -> d.getUserId().equals(userId))
                .collect(Collectors.toList());    }
}
