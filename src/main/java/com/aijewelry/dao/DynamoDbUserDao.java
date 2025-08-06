package com.aijewelry.dao;

import com.aijewelry.model.User;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DynamoDbUserDao implements UserDao {
    private final DynamoDbEnhancedClient enhancedClient;
    private final DynamoDbTable<User> userTable;

    public DynamoDbUserDao() {
        DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        this.enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.userTable = enhancedClient.table("Users", TableSchema.fromBean(User.class));
    }

    @Override
    public void saveUser(User user) {
        userTable.putItem(user);
        System.out.println("✅ User created or updated: " + user.getEmail());
    }

    @Override
    public User getUser(String email) {
        return userTable.getItem(r -> r.key(k -> k.partitionValue(email)));
    }
}
