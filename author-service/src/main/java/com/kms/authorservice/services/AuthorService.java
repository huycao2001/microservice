package com.kms.authorservice.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kms.authorservice.clients.InventoryClient;
import com.kms.authorservice.models.dtos.ResponseDTO;
import com.kms.authorservice.models.entities.Author;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    InventoryClient inventoryClient;

    @Autowired
    ObjectMapper objectMapper;


    public ResponseEntity<ResponseDTO<Author>> save(Author author) throws Exception {
        ResponseEntity<ResponseDTO<Author>> response = null;
        try{
            response = inventoryClient.saveAuthor(author);
        }catch(FeignException exception){
            exception.contentUTF8();
            log.info("Error when creating a new author" + exception.contentUTF8());
            ResponseDTO<Author> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;
    }
    public ResponseEntity<ResponseDTO<Author>> getAuthorByUuid(String authorUuid) throws Exception {
        ResponseEntity<ResponseDTO<Author>> response = null;
        try{
            response = inventoryClient.getAuthorByUuid(authorUuid);
        }catch(FeignException exception){
            log.error("Error when getting an author with uuid " + authorUuid + exception.getLocalizedMessage());
            ResponseDTO<Author> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;
    }

    public ResponseEntity<ResponseDTO<Author>> updateAuthor(Author author) throws Exception {
        ResponseEntity<ResponseDTO<Author>> response = null;
        try{
            response = inventoryClient.updateAuthor(author);
        }catch(FeignException exception){
            exception.contentUTF8();
            log.error("Error when updating an author with uuid " +  author.getUuid() + exception.contentUTF8());
            ResponseDTO<Author> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;
    }

    public ResponseEntity<ResponseDTO<Author>> deleteAuthor(String authorUuid) throws Exception {
        ResponseEntity<ResponseDTO<Author>> response = null;
        try{
            response = inventoryClient.deleteAuthor(authorUuid);
        }catch(FeignException exception){
            exception.contentUTF8();
            log.error("Error when updating an author with uuid " +  authorUuid + exception.contentUTF8());
            ResponseDTO<Author> responseDTO = objectMapper.readValue(exception.contentUTF8(), ResponseDTO.class);
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
        return response;

    }
}
