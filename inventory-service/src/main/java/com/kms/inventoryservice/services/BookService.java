package com.kms.inventoryservice.services;

import com.kms.inventoryservice.error.ObjectNotFoundException;
import com.kms.inventoryservice.models.entities.Book;
import com.kms.inventoryservice.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book save(Book book) throws Exception {
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
            throw new ObjectNotFoundException("Book can not be found with uuid " + uuid);
        }
        return result;
    }


    public List<Book> getAllBooks() throws Exception {
        List<Book> result =  bookRepository.getAllBooks();
        if(result == null){
            return new ArrayList<>();
        }

        return result;
    }
}
