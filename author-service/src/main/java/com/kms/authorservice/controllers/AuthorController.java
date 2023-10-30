package com.kms.authorservice.controllers;


import com.kms.authorservice.clients.InventoryClient;
import com.kms.authorservice.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    InventoryClient inventoryClient;

    @PostMapping
    public ResponseEntity<Author> save(@RequestBody @Validated Author book) throws Exception {
        try{
            return inventoryClient.saveBook(book);
        }catch (Exception exception){
            throw new Exception(exception.getLocalizedMessage());
        }
    }

    @GetMapping(value = "/{authorUuid}")
    public ResponseEntity<Author> getBookByUuid(@PathVariable String authorUuid) throws Exception {
        try{

            return inventoryClient.getAuthorByUuid(authorUuid);
        }catch (Exception exception){
            throw new Exception(exception.getLocalizedMessage());
        }
    }
}
