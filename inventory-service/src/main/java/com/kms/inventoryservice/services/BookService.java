package com.kms.inventoryservice.services;

import com.kms.inventoryservice.models.entities.Book;
import com.kms.inventoryservice.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book save(Book book){
        if(book.getUuid() == null){
            UUID uuid = UUID.randomUUID();
            book.setUuid(uuid.toString());
        }

        bookRepository.save(book);
        return book;
    }

    public Book getByUuid(String uuid) throws Exception {
        Book result =  bookRepository.getByUuid(uuid);
        if(result == null){
            throw new Exception("Book can not be found");
        }

        return result;
    }
}
