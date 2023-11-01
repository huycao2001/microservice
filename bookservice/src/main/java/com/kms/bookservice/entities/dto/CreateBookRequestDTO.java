package com.kms.bookservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequestDTO {
    private String title;
    private String isbn;
    private String authorUuid;
}
