package com.aijewelry.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class Order {
    private String orderId;
    private String userId;
    private String designId; // can be null
    private String material;
    private String size; //can be null
    private String designName; // or Project name
    private String orderStatus; // should be changed to enum in the future
    private String imgUrl;
    private String price;
}
