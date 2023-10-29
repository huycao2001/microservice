package com.kms.bookservice.entities;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName ="book")
public class Book {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String uuid;

    @DynamoDBAttribute
    @NotBlank(message = "title cannot be blank")
    private String title;

    @DynamoDBAttribute
    private String isbn;

    @DynamoDBAttribute
    @NotBlank(message = "authorUuid cannot be blank")
    private String authorUuid;
}
