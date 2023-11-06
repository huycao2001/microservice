package com.kms.authorservice.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kms.authorservice.clients.InventoryClient;
import com.kms.authorservice.models.dtos.ResponseDTO;
import com.kms.authorservice.models.entities.Author;
import com.kms.authorservice.services.AuthorService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    InventoryClient inventoryClient;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AuthorService authorService;

    @PostMapping
    public ResponseEntity<ResponseDTO<Author>> save(@RequestBody @Validated Author author) throws Exception {
        return authorService.save(author);
    }

    @GetMapping(value = "/{authorUuid}")
    public ResponseEntity<ResponseDTO<Author>> getAuthorByUuid(@PathVariable String authorUuid) throws Exception {
        return authorService.getAuthorByUuid(authorUuid);
    }


    @PutMapping
    public ResponseEntity<ResponseDTO<Author>> updateAuthor(@RequestBody @Validated Author author) throws Exception {
        return authorService.updateAuthor(author);
    }

    @DeleteMapping(value = "/{authorUuid}")
    public ResponseEntity<ResponseDTO<Author>> deleteAuthor(@PathVariable String authorUuid) throws Exception {
        return authorService.deleteAuthor(authorUuid);
    }

}
