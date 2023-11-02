package com.kms.inventoryservice.models.dto;

import com.kms.inventoryservice.models.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailDTO {
    String uuid;
    String title;
    String isbn;
    Author author;
    int quantity;
}