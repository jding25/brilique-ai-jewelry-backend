package com.aijewelry.service;

import com.aijewelry.dao.DynamoDbDesignDao;
import com.aijewelry.model.Design;
import com.aijewelry.model.DesignUploadRequest;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class DesignServiceImpl implements DesignService {

    private final S3ImageStorageService imageStorage = new S3ImageStorageService();
    private final DynamoDbDesignDao designDao = new DynamoDbDesignDao();

    @Override
    public Design saveDesign(DesignUploadRequest request) throws Exception {
        // TODO: to improve efficiency, we can return imageUrl first before saving to designDao?
        String imageUrl = imageStorage.uploadImage(request.imageBase64);
        System.out.println("imageUrl in DesignServiceImpl is "+imageUrl);

        Design design = new Design();
        design.setUserId(request.userId);
        design.setDesignId(UUID.randomUUID().toString());
        design.setUserPrompt(request.userPrompt);
        design.setStyle(request.style);
        design.setType(request.type);
        design.setEnhancedPrompt(request.enhancedPrompt);
        design.setImageUrl(imageUrl);
        design.setTimestamp(Instant.now());
        design.setAddToMarket(false);
        design.setMaterial(request.material);
        design.setPrice(request.price);
        design.setNumLikes(0);

        designDao.saveDesign(design);
        return design;
    }

    @Override
    public List<Design> getUserDesigns(String userId) throws Exception {
        return designDao.getDesignsByUser(userId);
    }

    @Override
    public List<Design> getMarketDesigns() throws Exception {
        return designDao.getMarketDesigns();
    }

    @Override
    public void setAddToMarket(String userId, String designId, Boolean addToMarket) throws Exception {
        designDao.setAddToMarket(userId, designId, addToMarket);
    }

    @Override
    public List<Design> getOnMarketDesignByUser(String userId) throws Exception {
        return designDao.getOnMarketDesignByUser(userId);
    }

    @Override
    public Design getDesign(String userId, String designId) throws Exception {
        return designDao.getDesign(userId, designId);
    }
}
