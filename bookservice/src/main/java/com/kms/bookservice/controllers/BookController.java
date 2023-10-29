package com.kms.bookservice.controllers;

import com.kms.bookservice.clients.InventoryClient;
import com.kms.bookservice.entities.Book;
import com.kms.bookservice.repositories.BookRepository;
import com.kms.bookservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private InventoryClient inventoryClient;

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody @Validated Book book) throws Exception {
        try{
            return inventoryClient.saveBook(book);
        }catch (Exception exception){
            throw new Exception(exception.getLocalizedMessage());
        }
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<Book> getBookByUuid(@PathVariable String uuid) throws Exception {
        try{

            return inventoryClient.getBookByUuid(uuid);
        }catch (Exception exception){
            throw new Exception(exception.getLocalizedMessage());
        }
    }

}
