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
    public void saveDesign(DesignUploadRequest request) throws Exception {
        String imageUrl = imageStorage.uploadImage(request.imageBase64);

        Design design = new Design();
        design.setUserId(request.userId);
        design.setDesignId(UUID.randomUUID().toString());
        design.setUserPrompt(request.userPrompt);
        design.setStyle(request.style);
        design.setType(request.type);
        design.setEnhancedPrompt(request.enhancedPrompt);
        design.setImageUrl(imageUrl);
        design.setTimestamp(Instant.now());

        designDao.saveDesign(design);
    }

    @Override
    public List<Design> getUserDesigns(String userId) throws Exception {
        return designDao.getDesignsByUser(userId);
    }
}
