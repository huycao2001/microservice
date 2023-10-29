package com.kms.inventoryservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class StorageService {
    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    public void createBucket(){
        CreateBucketRequest bucketRequest = CreateBucketRequest.builder().bucket("huybcao-bucket").build();

        s3Client.createBucket(bucketRequest);
    }

    public String uploadObject(MultipartFile multipartFile) throws Exception {
        try{
            byte[] bytes = multipartFile.getBytes();
            InputStream inputStream = new ByteArrayInputStream(bytes);
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(multipartFile.getOriginalFilename())
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, bytes.length));
            return "Object uploaded successfully";
        }catch (Exception exception){
            throw new Exception(exception.getLocalizedMessage());
        }
    }


}
