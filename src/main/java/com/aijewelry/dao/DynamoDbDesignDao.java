package com.aijewelry.dao;

import com.aijewelry.model.Design;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        int pageLimit = 10;

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
}
