package com.kms.inventoryservice.error;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String message){
        super(message);
    }
}
