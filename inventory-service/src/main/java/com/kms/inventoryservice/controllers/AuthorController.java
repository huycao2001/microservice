package com.kms.inventoryservice.controllers;


import com.kms.inventoryservice.models.entities.Author;
import com.kms.inventoryservice.models.entities.Book;
import com.kms.inventoryservice.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> save(@RequestBody Author author) throws Exception {
        try{
            Author result = authorService.save(author);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception){
            throw new Exception(exception.getLocalizedMessage());
        }
    }


    @GetMapping(value = "/{authorUuid}")
    public ResponseEntity<Author> getBookByUuid(@PathVariable String authorUuid) throws Exception {
        try{
            Author result = authorService.getByUuid(authorUuid);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception exception){
            throw new Exception(exception.getLocalizedMessage());
        }
    }
}
