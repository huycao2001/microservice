package com.kms.authorservice.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kms.authorservice.clients.InventoryClient;
import com.kms.authorservice.models.dto.ResponseDTO;
import com.kms.authorservice.models.entities.Author;
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

    @PostMapping
    public ResponseEntity<ResponseDTO<Author>> save(@RequestBody @Validated Author author) throws Exception {
        ResponseEntity<ResponseDTO<Author>> response = null;
        try{
            response = inventoryClient.saveAuthor(author);
        }catch(FeignException exception){
            exception.contentUTF8();
            log.info("Exception : " + exception.contentUTF8());
            ResponseDTO<Author> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;
    }

    @GetMapping(value = "/{authorUuid}")
    public ResponseEntity<ResponseDTO<Author>> getBookByUuid(@PathVariable String authorUuid) throws Exception {
        ResponseEntity<ResponseDTO<Author>> response = null;
        try{
            response = inventoryClient.getAuthorByUuid(authorUuid);
        }catch(FeignException exception){
            exception.contentUTF8();
            log.info("Exception : " + exception.contentUTF8());
            ResponseDTO<Author> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;
    }


    @PutMapping
    public ResponseEntity<ResponseDTO<Author>> updateAuthor(@RequestBody @Validated Author author) throws Exception {
        ResponseEntity<ResponseDTO<Author>> response = null;
        try{
            response = inventoryClient.updateAuthor(author);
        }catch(FeignException exception){
            exception.contentUTF8();
            log.info("Exception : " + exception.contentUTF8());
            ResponseDTO<Author> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;
    }

    @DeleteMapping(value = "/{authorUuid}")
    public ResponseEntity<ResponseDTO<Author>> deleteAuthor(@PathVariable String authorUuid) throws Exception {
        ResponseEntity<ResponseDTO<Author>> response = null;
        try{
            response = inventoryClient.deleteAuthor(authorUuid);
        }catch(FeignException exception){
            exception.contentUTF8();
            log.info("Exception : " + exception.contentUTF8());
            ResponseDTO<Author> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;

    }

}
