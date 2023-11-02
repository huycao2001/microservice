package com.kms.inventoryservice.services;

import com.kms.inventoryservice.error.ObjectNotFoundException;
import com.kms.inventoryservice.models.dto.BookDetailDTO;
import com.kms.inventoryservice.models.entities.Author;
import com.kms.inventoryservice.models.entities.Book;
import com.kms.inventoryservice.models.entities.StockInfo;
import com.kms.inventoryservice.repositories.AuthorRepository;
import com.kms.inventoryservice.repositories.BookRepository;
import com.kms.inventoryservice.repositories.StockInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    StockInfoRepository stockInfoRepository;

    public Book save(Book book) throws Exception {
        if(book.getUuid() == null){
            UUID uuid = UUID.randomUUID();
            book.setUuid(uuid.toString());
        }
        Author author = authorRepository.getByUuid(book.getAuthorUuid());
        if(author == null){
            throw new ObjectNotFoundException("Can not add book due to invalid authorUuid");
        }
        bookRepository.save(book);
        return book;
    }

    public BookDetailDTO getByUuid(String uuid) throws Exception {
        Book book =  bookRepository.getByUuid(uuid);

        if(book == null){
            throw new ObjectNotFoundException("Book can not be found with uuid " + uuid);
        }

        return convertBooktoBookDetailDTO(book);
    }


    public List<BookDetailDTO> getAllBooks() throws Exception {
        List<Book> books =  bookRepository.getAllBooks();
        List<BookDetailDTO> result = new ArrayList<>();
        if(!CollectionUtils.isEmpty(books)){
            for(Book book : books){
                result.add(convertBooktoBookDetailDTO(book));
            }
        }

        return result;
    }

    private BookDetailDTO convertBooktoBookDetailDTO(Book book){
        StockInfo stockInfo = stockInfoRepository.findByBookUuid(book.getUuid());
        Author author = authorRepository.getByUuid(book.getAuthorUuid());
        int quantity;
        if(stockInfo == null){
            quantity = 0;
        }else{
            quantity = stockInfo.getQuantity();
        }
        BookDetailDTO bookDetailDTO = BookDetailDTO.builder()
                .uuid(book.getUuid())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .author(author)
                .quantity(quantity)
                .build();
        return bookDetailDTO;
    }


}
