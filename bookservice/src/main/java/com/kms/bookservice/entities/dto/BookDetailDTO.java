package com.kms.bookservice.entities.dto;

import com.kms.bookservice.entities.models.Author;

public class BookDetailDTO {
    String uuid;

    String firstName;

    String lastName;

    String email;

    Author author;

    int quantity;
}
