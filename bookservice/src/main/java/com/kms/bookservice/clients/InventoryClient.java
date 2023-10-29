package com.kms.bookservice.clients;

import com.kms.bookservice.entities.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "inventoryClient", url = "http://localhost:8081/api/v1/books")
public interface InventoryClient {
    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    ResponseEntity<Book> getBookByUuid(@PathVariable String uuid);

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Book> saveBook(@RequestBody Book book);
}
