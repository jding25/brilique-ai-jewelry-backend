package com.aijewelry.model;

import java.time.Instant;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
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
    private String enhancedPrompt;
    private String imageUrl;
    private Instant timestamp;
    private Boolean addToMarket;
    private Integer numLikes;
    private Integer price;
    private String material;
    private String description;
    private String designName;

    public Design() {}

    public Design(String userId, String designId, String userPrompt, String type, String style,
                  String imageUrl, String enhancedPrompt, Instant timestamp, Boolean addToMarket, Integer numLikes,
                  Integer price, String material, String description, String designName) {
        this.userId = userId;
        this.designId = designId;
        this.userPrompt = userPrompt;
        this.type = type;
        this.style = style;
        this.imageUrl = imageUrl;
        this.enhancedPrompt = enhancedPrompt;
        this.timestamp = timestamp;
        this.addToMarket = addToMarket;
        this.numLikes = numLikes;
        this.price = price;
        this.material = material;
        this.description = description;
        this.designName = designName;
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

    public String getEnhancedPrompt() {
        return enhancedPrompt;
    }

    public void setEnhancedPrompt(String enhancedPrompt) {
        this.enhancedPrompt = enhancedPrompt;
    }

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

    public void setAddToMarket(Boolean addToMarket) { this.addToMarket = addToMarket; }
    @DynamoDbAttribute("addToMarket")
    public Boolean getAddToMarket() {
        return addToMarket;
    }

    public void setNumLikes(Integer numLikes) {this.numLikes = numLikes; }

    public Integer getNumLikes() {
        return numLikes;
    }

    public void setPrice(Integer price) {this.price = price;}

    public Integer getPrice(){return this.price;}

    public void setMaterial(String material) {this.material = material;}

    public String getMaterial(){return this.material;}

    public void setDescription(String description) {this.description = description;}

    public String getDescription(){return this.description;}

    public void setDesignName(String designName) {this.designName = designName;}

    public String getDesignName(){return this.designName;}
}
