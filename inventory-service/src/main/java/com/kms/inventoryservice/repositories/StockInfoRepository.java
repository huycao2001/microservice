package com.kms.inventoryservice.repositories;


import com.kms.inventoryservice.models.entities.Book;
import com.kms.inventoryservice.models.entities.StockInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class StockInfoRepository {

    @Autowired
    DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public StockInfo save(StockInfo stockInfo) throws  Exception{
        DynamoDbTable<StockInfo> bookTable = dynamoDbEnhancedClient.table("stock_infos", TableSchema.fromBean(StockInfo.class));
        bookTable.putItem(stockInfo);
        return stockInfo;
    }
}
