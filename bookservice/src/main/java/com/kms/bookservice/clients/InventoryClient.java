package com.kms.bookservice.clients;

import com.kms.bookservice.entities.dto.BookDetailDTO;
import com.kms.bookservice.entities.dto.CreateBookRequestDTO;
import com.kms.bookservice.entities.dto.ResponseDTO;
import com.kms.bookservice.entities.models.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "inventoryClient", url = "${inventory.book.service.url}")
public interface InventoryClient {

    @GetMapping("/{uuid}")
    ResponseEntity<ResponseDTO<BookDetailDTO>> getBookByUuid(@PathVariable String uuid);


    @GetMapping
    ResponseEntity<ResponseDTO<List<BookDetailDTO>>>  getAllBooks();

    @PostMapping
    ResponseEntity<ResponseDTO<Book>> saveBook(@RequestBody CreateBookRequestDTO createBookRequestDTO);
}
