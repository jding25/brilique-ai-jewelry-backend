package com.aijewelry.dao;

import com.aijewelry.model.Design;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.ArrayList;
import java.util.List;

public class DynamoDbDesignDao implements DesignDao{
    private final DynamoDbEnhancedClient enhancedClient;
    private final DynamoDbTable<Design> designTable;

    public DynamoDbDesignDao() {
        DynamoDbClient dynamoDb = DynamoDbClient.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        this.enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDb)
                .build();

        this.designTable = enhancedClient.table("JewelryDesigns", TableSchema.fromBean(Design.class));
    }
    @Override
    public void saveDesign(Design design) {
        designTable.putItem(design);
    }

    @Override
    public List<Design> getDesignsByUser(String userId) throws Exception {
        QueryConditional query = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(userId).build());

        List<Design> results = new ArrayList<>();

        designTable.query(query).items().forEach(results::add);
        return results;
    }


}
