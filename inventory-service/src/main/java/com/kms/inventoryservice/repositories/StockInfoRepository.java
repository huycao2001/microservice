package com.kms.inventoryservice.repositories;


import com.kms.inventoryservice.models.entities.Book;
import com.kms.inventoryservice.models.entities.StockInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.List;

@Repository
public class StockInfoRepository {

    @Autowired
    DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public StockInfo save(StockInfo stockInfo) throws  Exception{
        DynamoDbTable<StockInfo> stockInfoTable = dynamoDbEnhancedClient.table("stock_infos", TableSchema.fromBean(StockInfo.class));
        stockInfoTable.putItem(stockInfo);
        return stockInfo;
    }


    public StockInfo findByBookUuid(String bookUuid){
        DynamoDbTable<StockInfo> stockInfoTable = dynamoDbEnhancedClient.table("stock_infos", TableSchema.fromBean(StockInfo.class));
        List<StockInfo> stockInfos = stockInfoTable.scan().items().stream().toList();
        for(StockInfo stockInfo : stockInfos){
            if(stockInfo.getBookUuid().equals(bookUuid)){
                return stockInfo;
            }
        }
        return  null;
    }
}
