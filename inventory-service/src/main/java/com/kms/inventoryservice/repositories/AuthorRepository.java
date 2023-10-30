package com.kms.inventoryservice.repositories;

import com.kms.inventoryservice.models.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class AuthorRepository {

    @Autowired
    DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public Author save(Author author){
        DynamoDbTable<Author> authorTable = dynamoDbEnhancedClient.table("authors", TableSchema.fromBean(Author.class));
        authorTable.putItem(author);
        return author;
    }


    public Author getByUuid(String authorUuid){
        DynamoDbTable<Author> authorTable = dynamoDbEnhancedClient.table("authors", TableSchema.fromBean(Author.class));
        return authorTable.getItem(Key.builder().partitionValue(authorUuid).build());
    }
}
