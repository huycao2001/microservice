package com.kms.inventoryservice.repositories;

import com.kms.inventoryservice.models.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;


import java.util.List;


@Repository
public class BookRepository {
    @Autowired
    DynamoDbEnhancedClient dynamoDbEnhancedClient;

    private static String BOOK_TABLE = "books";

   public Book save(Book book) throws Exception{
        DynamoDbTable<Book> bookTable = dynamoDbEnhancedClient.table(BOOK_TABLE,TableSchema.fromBean(Book.class));
        bookTable.putItem(book);
        return book;

   }

   public Book getByUuid(String uuid) throws Exception {
       DynamoDbTable<Book> bookTable = dynamoDbEnhancedClient.table(BOOK_TABLE,TableSchema.fromBean(Book.class));
       return bookTable.getItem(Key.builder().partitionValue(uuid).build());

   }


    public List<Book> getAllBooks() throws Exception {
        DynamoDbTable<Book> bookTable = dynamoDbEnhancedClient.table(BOOK_TABLE,TableSchema.fromBean(Book.class));
        List<Book> books = bookTable.scan().items().stream().toList();
        return books;
    }


}
