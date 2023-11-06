package com.kms.bookservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kms.bookservice.clients.InventoryClient;
import com.kms.bookservice.entities.dto.BookDetailDTO;
import com.kms.bookservice.entities.dto.CreateBookRequestDTO;
import com.kms.bookservice.entities.dto.ResponseDTO;
import com.kms.bookservice.entities.models.Book;
import com.kms.bookservice.services.BookService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<ResponseDTO<Book>> save(@RequestBody @Validated CreateBookRequestDTO createBookRequestDTO) throws Exception {
        return bookService.save(createBookRequestDTO);
    }

    @GetMapping(value = "/{bookUuid}")
    public ResponseEntity<ResponseDTO<BookDetailDTO>> getBookByUuid(@PathVariable String bookUuid) throws JsonProcessingException {
        return bookService.getBookByUuid(bookUuid);
    }


    @GetMapping
    public ResponseEntity<ResponseDTO<List<BookDetailDTO>>> getAllBooks() throws Exception {
        return bookService.getAllBooks();
    }
}
