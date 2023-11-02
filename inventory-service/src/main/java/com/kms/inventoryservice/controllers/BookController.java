package com.kms.inventoryservice.controllers;


import com.kms.inventoryservice.models.dto.BookDetailDTO;
import com.kms.inventoryservice.models.dto.CreateBookRequestDTO;
import com.kms.inventoryservice.models.dto.ResponseDTO;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookService bookService;


    @PostMapping
    public ResponseEntity<ResponseDTO<Book>> save(@RequestBody CreateBookRequestDTO createBookRequestDTO) throws Exception {
            Book book = Book.builder()
                    .title(createBookRequestDTO.getTitle())
                    .isbn(createBookRequestDTO.getIsbn())
                    .authorUuid(createBookRequestDTO.getAuthorUuid())
                    .build();
            Book result = bookService.save(book);
            ResponseDTO<Book> responseDTO = new ResponseDTO<>(0, "Success", result);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/{uuid}")
    public ResponseEntity<ResponseDTO<BookDetailDTO>> getBookByUuid(@PathVariable String uuid) throws Exception {
        BookDetailDTO result = bookService.getByUuid(uuid);
        ResponseDTO<BookDetailDTO> responseDTO = new ResponseDTO<>(0, "Success", result);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    @GetMapping
    public ResponseEntity<ResponseDTO<List<BookDetailDTO>>> getAllBooks() throws Exception {
        List<BookDetailDTO> result = bookService.getAllBooks();
        ResponseDTO<List<BookDetailDTO>> responseDTO = new ResponseDTO<>(0, "Success", result);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
