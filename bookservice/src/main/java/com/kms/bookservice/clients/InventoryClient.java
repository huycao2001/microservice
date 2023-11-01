package com.kms.bookservice.clients;

import com.kms.bookservice.entities.dto.CreateBookRequestDTO;
import com.kms.bookservice.entities.dto.ResponseDTO;
import com.kms.bookservice.entities.models.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "inventoryClient", url = "http://localhost:8081/api/v1/books")
public interface InventoryClient {
    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    ResponseEntity<ResponseDTO<Book>> getBookByUuid(@PathVariable String uuid);


    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<ResponseDTO<List<Book>>>  getAllBooks();

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<ResponseDTO<Book>> saveBook(@RequestBody CreateBookRequestDTO createBookRequestDTO);
}
