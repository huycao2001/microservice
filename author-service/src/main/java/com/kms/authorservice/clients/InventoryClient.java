package com.kms.authorservice.clients;


import com.kms.authorservice.models.dto.ResponseDTO;
import com.kms.authorservice.models.entities.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "inventoryClient", url = "http://localhost:8081/api/v1/authors")
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<ResponseDTO<Author>> saveAuthor(@RequestBody Author author);


    @RequestMapping(method = RequestMethod.GET, value = {"/{authorUuid}"})
    ResponseEntity<ResponseDTO<Author>> getAuthorByUuid(@PathVariable String authorUuid);


    @RequestMapping(method = RequestMethod.DELETE, value = {"/{authorUuid}"})
    ResponseEntity<ResponseDTO<Author>> deleteAuthor(@PathVariable String authorUuid);

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<ResponseDTO<Author>> updateAuthor(@RequestBody Author author);
}
