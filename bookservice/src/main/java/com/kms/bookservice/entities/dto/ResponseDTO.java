package com.kms.bookservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private int resultCode;
    private String resultMessage;
    private T payload;
}
