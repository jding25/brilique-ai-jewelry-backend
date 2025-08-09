package com.aijewelry.model;

public class ModifyMarketRequest {
    private String userId;
    private String designId;
    private Boolean addToMarket;
    public ModifyMarketRequest() {}
    public String getUserId() { return userId; }
    public void setUserId(String v) { userId = v; }
    public String getDesignId() { return designId; }
    public void setDesignId(String v) { designId = v; }
    public Boolean getAddToMarket() { return addToMarket; }
    public void setAddToMarket(Boolean v) { addToMarket = v; }
}
