package com.kms.inventoryservice.services;



import com.kms.inventoryservice.errors.ObjectNotFoundException;
import com.kms.inventoryservice.models.entities.Author;
import com.kms.inventoryservice.repositories.AuthorRepository;
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
            throw new ObjectNotFoundException("Author can not be found with uuid " + uuid);
        }
        return result;
    }


    public Author updateAuthor(Author author) throws Exception {
        Author result =  authorRepository.updateAuthor(author);
        if(result == null){
            throw new Exception("Can not update author with uuid " + author.getUuid());
        }
        return result;
    }

    public Author deleteAuthor(String authorUuid) throws Exception {
        Author targetAuthor = authorRepository.getByUuid(authorUuid);
        if(targetAuthor == null){
            throw new ObjectNotFoundException("Author can not be found with uuid " + authorUuid);
        }
        Author result =  authorRepository.deleteAuthor(authorUuid);
        if(result == null){
            throw new Exception("Can not delete author with uuid " + authorUuid);
        }
        return result;
    }
}
