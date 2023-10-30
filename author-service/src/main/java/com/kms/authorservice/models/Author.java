package com.kms.authorservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    String uuid;

    String firstName;

    String lastName;

    String email;
}
