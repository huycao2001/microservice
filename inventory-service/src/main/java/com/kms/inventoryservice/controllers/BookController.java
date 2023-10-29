package com.kms.inventoryservice.controllers;


import com.kms.inventoryservice.models.entities.Book;
import com.kms.inventoryservice.repositories.BookRepository;
import com.kms.inventoryservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookService bookService;


    @PostMapping
    public ResponseEntity<Book> save(@RequestBody  Book book) throws Exception {
        try{
            Book result = bookService.save(book);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception){
            throw new Exception(exception.getLocalizedMessage());
        }
    }


    @GetMapping(value = "/{uuid}")
    public ResponseEntity<Book> getBookByUuid(@PathVariable String uuid) throws Exception {
        try{
            Book result = bookService.getByUuid(uuid);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception){
            throw new Exception(exception.getLocalizedMessage());
        }
    }
}
