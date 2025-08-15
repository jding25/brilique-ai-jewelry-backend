package com.aijewelry.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;
import java.util.Set;

@DynamoDbBean
public class User {
    private String email;
    private String name;
    private String profilePicUrl;
    private Set<String> likedDesigns;
    private List<String> orders;

    public User() {}

    public User(String email, String name, String profilePicUrl, Set<String> likedDesigns, List<String> orders) {
        this.email = email;
        this.name = name;
        this.profilePicUrl = profilePicUrl;
        this.likedDesigns = likedDesigns;
        this.orders = orders;
    }

    @DynamoDbPartitionKey
    public String getEmail(){return this.email;}

    public void setEmail(String email) {this.email = email;}

    public String getName(){return this.name;}

    public void setName(String name){this.name = name;}

    public String getProfilePicUrl(){return this.profilePicUrl;}

    public void setProfilePicUrl(String profilePicUrl){this.profilePicUrl = profilePicUrl;}

    public Set<String> getLikedDesigns(){return this.likedDesigns;}

    public void setLikedDesigns(Set<String> likedDesigns){this.likedDesigns = likedDesigns;}

    public List getOrders(){return this.orders;}

    public void setOrders(List orders){this.orders = orders;}

}
