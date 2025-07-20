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
    public String saveDesign(DesignUploadRequest request) throws Exception {
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

        designDao.saveDesign(design);
        return imageUrl;
    }

    @Override
    public List<Design> getUserDesigns(String userId) throws Exception {
        return designDao.getDesignsByUser(userId);
    }
}
