package com.kms.inventoryservice.controllers;


import com.kms.inventoryservice.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;
    @GetMapping
    public void createBucket(){
        try{
            storageService.createBucket();
        }catch (Exception exception){
            System.out.println(exception);
        }
    }


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        try{
            return storageService.uploadObject(multipartFile);
        }catch (Exception exception){
            throw  new Exception(exception.getLocalizedMessage());
        }
    }

}
