package com.kms.authorservice.models.dto;

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

