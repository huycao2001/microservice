package com.kms.inventoryservice.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockInfo {

    String uuid;
    String bookUuid;
    int quantity;


    @DynamoDbPartitionKey
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public String getBookUuid() {
        return bookUuid;
    }

    public void setBookUuid(String bookUuid) {
        this.bookUuid = bookUuid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
