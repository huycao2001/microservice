package com.kms.authorservice.clients;


import com.kms.authorservice.models.dtos.ResponseDTO;
import com.kms.authorservice.models.entities.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "inventoryClient", url = "${inventory.author.service.url}")
public interface InventoryClient {

    @PostMapping
    ResponseEntity<ResponseDTO<Author>> saveAuthor(@RequestBody Author author);


    @GetMapping("/{authorUuid}")
    ResponseEntity<ResponseDTO<Author>> getAuthorByUuid(@PathVariable String authorUuid);


    @DeleteMapping("/{authorUuid}")
    ResponseEntity<ResponseDTO<Author>> deleteAuthor(@PathVariable String authorUuid);

    @PutMapping
    ResponseEntity<ResponseDTO<Author>> updateAuthor(@RequestBody Author author);
}
