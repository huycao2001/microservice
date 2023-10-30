package com.kms.authorservice.clients;


import com.kms.authorservice.models.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "inventoryClient", url = "http://localhost:8081/api/v1/authors")
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Author> saveBook(@RequestBody Author book);


    @RequestMapping(method = RequestMethod.GET, value = {"/{authorUuid}"})
    ResponseEntity<Author> getAuthorByUuid(@PathVariable String authorUuid);
}
