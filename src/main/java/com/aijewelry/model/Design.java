package com.aijewelry.model;

import java.time.Instant;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class Design {
    private String userId;
    private String designId;
    private String type;
    private String style;
    private String userPrompt;
//    private String enhancedPrompt;
    private String imageUrl;
    private Instant timestamp;

    public Design() {}

    public Design(String userId, String designId, String userPrompt, String type, String style,
                  String imageUrl, Instant timestamp) {
        this.userId = userId;
        this.designId = designId;
        this.userPrompt = userPrompt;
        this.type = type;
        this.style = style;
        this.imageUrl = imageUrl;
//        this.enhancedPrompt = enhancedPrompt;
        this.timestamp = timestamp;
    }

    @DynamoDbPartitionKey
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDbSortKey
    public String getDesignId() {
        return designId;
    }

    public void setDesignId(String designId) {
        this.designId = designId;
    }

    public String getUserPrompt() {
        return userPrompt;
    }

    public void setUserPrompt(String userPrompt) {
        this.userPrompt = userPrompt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

//    public String getEnhancedPrompt() {
//        return enhancedPrompt;
//    }
//
//    public void setEnhancedPrompt(String enhancedPrompt) {
//        this.enhancedPrompt = enhancedPrompt;
//    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
