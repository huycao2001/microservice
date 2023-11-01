package com.kms.bookservice.services;


import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.kms.bookservice.entities.models.Book;
import com.kms.bookservice.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public Book save(Book book) throws Exception, AmazonDynamoDBException {
            try{
                bookRepository.save(book);
                return book;
            }catch (AmazonDynamoDBException exception){
                throw new Exception(exception.getLocalizedMessage());
            }
    }


}
