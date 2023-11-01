package com.kms.bookservice.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kms.bookservice.entities.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Book save(Book book){
        dynamoDBMapper.save(book);
        return book;
    }

}
