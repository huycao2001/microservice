package com.kms.bookservice.entities.dto;

import com.kms.bookservice.entities.models.Author;
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