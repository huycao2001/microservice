package com.kms.inventoryservice.models.dto;

import com.kms.inventoryservice.models.entities.Author;

public class BookDetailDTO {
    String uuid;
    String firstName;
    String lastName;
    String email;
    Author author;
    int quantity;
}