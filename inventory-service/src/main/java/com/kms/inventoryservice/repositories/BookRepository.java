package com.kms.inventoryservice.repositories;

import com.kms.inventoryservice.models.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;


@Repository
public class BookRepository {
    @Autowired
    DynamoDbEnhancedClient dynamoDbEnhancedClient;



   public Book save(Book book){
        DynamoDbTable<Book> bookTable = dynamoDbEnhancedClient.table("books", TableSchema.fromBean(Book.class));
        bookTable.putItem(book);
        return book;

   }

   public Book getByUuid(String uuid) throws Exception {
       DynamoDbTable<Book> bookTable = dynamoDbEnhancedClient.table("books", TableSchema.fromBean(Book.class));
       return bookTable.getItem(Key.builder().partitionValue(uuid).build());

   }


}
