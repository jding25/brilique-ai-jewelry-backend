package com.aijewelry.dao;

import com.aijewelry.model.Design;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

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
        try {
            QueryConditional query = QueryConditional
                    .keyEqualTo(Key.builder().partitionValue(userId).build());

            List<Design> results = new ArrayList<>();

            designTable.query(query).items().forEach(results::add);
            System.out.println(results);
            return results;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    @Override
    public java.util.List<Design> getMarketDesigns() {
        int pageLimit = 50;

        // Server‑side filter (still a scan, but DynamoDB drops non-matching items early)
        Expression onlyMarket = Expression.builder()
                .expression("addToMarket = :t")
                .putExpressionValue(":t", AttributeValue.builder().bool(true).build())
                .build();

        ScanEnhancedRequest req = ScanEnhancedRequest.builder()
                .limit(pageLimit)            // first page only
                .filterExpression(onlyMarket)
                .build();

        PageIterable<Design> pages = designTable.scan(req);
        return pages.stream()
                .findFirst()
                .map(Page::items)
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
//        return designTable.scan().items().stream().filter(design -> Boolean.TRUE.equals(design.isAddToMarket())).collect(Collectors.toList());
    }

    @Override
    public void setAddToMarket(String userId, String designId, Boolean addToMarket) throws Exception{
        try {
            Key key = Key.builder()
                    .partitionValue(userId)
                    .sortValue(designId)
                    .build();

            Design design = designTable.getItem(key);
            if (design == null) {
                throw new IllegalArgumentException("Design not found for userId=" + userId + " and designId=" + designId);
            }

            System.out.println("Before update - addToMarket: " + design.getAddToMarket());
            design.setAddToMarket(addToMarket);
            System.out.println("After setting - addToMarket: " + design.getAddToMarket());

            // Use putItem instead of updateItem to ensure the change persists
            designTable.putItem(design);

            // Verify the update by reading it back
            Design updatedDesign = designTable.getItem(key);
            System.out.println("After update - addToMarket: " + updatedDesign.getAddToMarket());
        } catch (Exception e) {
            System.err.println("Failed to update addToMarket: " + e.getMessage());
            throw e;
        }

    }
}
