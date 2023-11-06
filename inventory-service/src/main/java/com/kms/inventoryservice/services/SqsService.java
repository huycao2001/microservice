package com.kms.inventoryservice.services;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

@Slf4j
@Service
public class SqsService {

    @Autowired
    StorageService storageService;

    @Autowired
    CSVService csvService;

    @SqsListener(value = "InventoryQueue")
    void receiveMessage(String message) throws Exception {
        String key = processS3Event(message);
        log.info("Receive message from queue " + message);
        log.info("Object uploaded file name : " + key);
        byte[] bytes = storageService.getObject(key);
        csvService.processCSV(bytes);
    }


    private String processS3Event(String s3EventJson) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode s3Event = objectMapper.readTree(s3EventJson);
            String objectKey = s3Event.get("Records").get(0).get("s3").get("object").get("key").asText();
            return objectKey;
        } catch (Exception e) {
            log.error("Error when extracting the file name from the queue message !");
            throw new Exception(e.getMessage());
        }
    }
}
