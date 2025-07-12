package com.aijewelry.model;

public class Design {
    private String id;
    private String userId;
    private String imageUrl;
    private String prompt;

    public Design() {}

    public Design(String id, String userId, String imageUrl, String prompt, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.prompt = prompt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
