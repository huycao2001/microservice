package com.kms.inventoryservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequestDTO {
    private String title;
    private String isbn;
    private String authorUuid;

}