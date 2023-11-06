package com.kms.inventoryservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class StorageService {
    @Autowired
    private S3Client s3Client;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    public void createBucket(){
        try{
            CreateBucketRequest bucketRequest = CreateBucketRequest.builder().bucket(bucketName).build();
            s3Client.createBucket(bucketRequest);
        }catch (Exception exception){
            log.error("Error when creating bucket " + bucketName, exception);
        }
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
            log.error("Error when uploading file : " + multipartFile.getOriginalFilename(), exception);
            throw new Exception(exception.getLocalizedMessage());
        }
    }

    public byte[] getObject(String fileName) throws Exception{
        try{
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(fileName)
                    .bucket(bucketName)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(objectRequest);
            return objectBytes.asByteArray();
        }catch (Exception exception){
            log.error("Error when downloading file : " + fileName, exception);
            throw new Exception("Error getting the file : " + fileName);
        }
    }


}
