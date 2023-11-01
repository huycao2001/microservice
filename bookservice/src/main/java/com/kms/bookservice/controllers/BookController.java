package com.kms.bookservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kms.bookservice.clients.InventoryClient;
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
        ResponseEntity<ResponseDTO<Book>> response = null;
        try{
            response = inventoryClient.saveBook(createBookRequestDTO);
        }catch(FeignException exception){
            exception.contentUTF8();
            log.info("Exception : " + exception.contentUTF8());
            ResponseDTO<Book> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<ResponseDTO<Book>> getBookByUuid(@PathVariable String uuid) throws JsonProcessingException {
            ResponseEntity<ResponseDTO<Book>> response = null;
            try{
                log.info("Call get book by uuid");
                response = inventoryClient.getBookByUuid(uuid);

            }catch(FeignException exception){
                exception.contentUTF8();
                log.info("Exception : " + exception.contentUTF8());
                ResponseDTO<Book> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
            }
            return response;
    }


    @GetMapping
    public ResponseEntity<ResponseDTO<List<Book>>> getAllBooks() throws Exception {
        ResponseEntity<ResponseDTO<List<Book>>> response = null;
        try{

            response =  inventoryClient.getAllBooks();
        }catch(FeignException exception){
            exception.contentUTF8();
            log.info("Exception : " + exception.contentUTF8());
            ResponseDTO<List<Book>> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;
    }

}
