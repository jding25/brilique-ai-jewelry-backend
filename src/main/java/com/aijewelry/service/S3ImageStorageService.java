package com.aijewelry.service;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.Base64;
import java.util.UUID;

public class S3ImageStorageService {
    private static final String BUCKET_NAME = "my-jewelry-images";
    private static final String REGION = "eu-north-1";

    private final S3Client s3 = S3Client.builder()
            .region(Region.of(REGION))
            .credentialsProvider(DefaultCredentialsProvider.create())
            .build();

    public String uploadImage(String base64) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(base64.replaceFirst("^data:image/[^;]+;base64,", ""));
        String fileName = "uploads/" + UUID.randomUUID() + ".png";

        PutObjectRequest putReq = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(fileName)
                .acl("public-read")
                .contentType("image/png")
                .build();

        s3.putObject(putReq, RequestBody.fromBytes(bytes));

        return "https://" + BUCKET_NAME + ".s3." + REGION + ".amazonaws.com/" + fileName;
        }

}
