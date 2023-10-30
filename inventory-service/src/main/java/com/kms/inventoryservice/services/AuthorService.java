package com.kms.inventoryservice.services;



import com.kms.inventoryservice.models.entities.Author;
import com.kms.inventoryservice.repositories.AuthorRepository;
import com.kms.inventoryservice.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author save(Author author){
        if(author.getUuid() == null){
            UUID uuid = UUID.randomUUID();
            author.setUuid(uuid.toString());
        }

        authorRepository.save(author);
        return author;
    }

    public Author getByUuid(String uuid) throws Exception {
        Author result =  authorRepository.getByUuid(uuid);
        if(result == null){
            throw new Exception("Author can not be found");
        }

        return result;
    }
}
