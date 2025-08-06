package com.aijewelry.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class User {
    private String email;
    private String name;
    private String profilePicUrl;

    public User() {}

    public User(String email, String name, String profilePicUrl) {
        this.email = email;
        this.name = name;
        this.profilePicUrl = profilePicUrl;
    }

    @DynamoDbPartitionKey
    public String getEmail(){return this.email;}

    public void setEmail(String email) {this.email = email;}

    public String getName(){return this.name;}

    public void setName(String name){this.name = name;}

    public String getProfilePicUrl(){return this.profilePicUrl;}

    public void setProfilePicUrl(String profilePicUrl){this.profilePicUrl = profilePicUrl;}

}
