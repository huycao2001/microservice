package com.kms.bookservice.services;


import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kms.bookservice.clients.InventoryClient;
import com.kms.bookservice.entities.dto.BookDetailDTO;
import com.kms.bookservice.entities.dto.CreateBookRequestDTO;
import com.kms.bookservice.entities.dto.ResponseDTO;
import com.kms.bookservice.entities.models.Book;
import com.kms.bookservice.repositories.BookRepository;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class BookService {
    @Autowired
    InventoryClient inventoryClient;

    @Autowired
    ObjectMapper objectMapper;

    public ResponseEntity<ResponseDTO<Book>> save(CreateBookRequestDTO createBookRequestDTO) throws Exception {
        ResponseEntity<ResponseDTO<Book>> response = null;
        try{
            response = inventoryClient.saveBook(createBookRequestDTO);
        }catch(FeignException exception){
            exception.contentUTF8();
            log.error("Error when creating a new book " + exception.contentUTF8());
            ResponseDTO<Book> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;
    }


    public ResponseEntity<ResponseDTO<BookDetailDTO>> getBookByUuid(String bookUuid) throws JsonProcessingException {
        ResponseEntity<ResponseDTO<BookDetailDTO>> response = null;
        try{
            response = inventoryClient.getBookByUuid(bookUuid);
        }catch(FeignException exception){
            exception.contentUTF8();
            log.error("Error when getting a book with uuid " + bookUuid + exception.contentUTF8());
            ResponseDTO<BookDetailDTO> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;
    }

    public ResponseEntity<ResponseDTO<List<BookDetailDTO>>> getAllBooks() throws Exception, FeignException.FeignClientException {
        ResponseEntity<ResponseDTO<List<BookDetailDTO>>> response = null;
        try{
            response =  inventoryClient.getAllBooks();
        }catch(FeignException exception){
            exception.contentUTF8();
            log.error("Error when calling getAllBooks service:" + exception.contentUTF8());
            ResponseDTO<List<BookDetailDTO>> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;
    }


}
