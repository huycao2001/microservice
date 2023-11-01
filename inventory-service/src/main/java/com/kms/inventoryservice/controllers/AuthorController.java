package com.kms.inventoryservice.controllers;


import com.kms.inventoryservice.models.dto.ResponseDTO;
import com.kms.inventoryservice.models.entities.Author;
import com.kms.inventoryservice.models.entities.Book;
import com.kms.inventoryservice.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping
    public ResponseEntity<ResponseDTO<Author>> save(@RequestBody Author author) throws Exception {
        Author result = authorService.save(author);
        ResponseDTO<Author> responseDTO = new ResponseDTO<>(0, "Success", result);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/{authorUuid}")
    public ResponseEntity<ResponseDTO<Author>> getAuthorByUuid(@PathVariable String authorUuid) throws Exception {
        Author result = authorService.getByUuid(authorUuid);
        ResponseDTO<Author> responseDTO = new ResponseDTO<>(0, "Success", result);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<ResponseDTO<Author>> updateAuthor(@RequestBody Author author) throws Exception {
        Author result = authorService.updateAuthor(author);
        ResponseDTO<Author> responseDTO = new ResponseDTO<>(0, "Success", result);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{authorUuid}")
    public ResponseEntity<ResponseDTO<Author>> deleteAuthor(@PathVariable String authorUuid) throws Exception {
        Author result = authorService.deleteAuthor(authorUuid);
        ResponseDTO<Author> responseDTO = new ResponseDTO<>(0, "Success", result);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
