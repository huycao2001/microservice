package com.kms.inventoryservice.services;


import com.kms.inventoryservice.models.entities.Book;
import com.kms.inventoryservice.models.entities.StockInfo;
import com.kms.inventoryservice.repositories.BookRepository;
import com.kms.inventoryservice.repositories.StockInfoRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

@Service
@Slf4j
public class CSVService {

    @Autowired
    StockInfoRepository stockInfoRepository;

    @Autowired
    BookRepository bookRepository;

    public void processCSV(byte[] csvBytes) throws Exception {
        InputStream targetStream = new ByteArrayInputStream(csvBytes);
        CSVReader reader = new CSVReader(new InputStreamReader(targetStream));
        String[] record = null;
        boolean isFirstRow = true;
        String stockInfoUuid;
        String bookUuid;
        int quantity;
        while ((record = reader.readNext()) != null) {
            if(isFirstRow){
                isFirstRow = false;
                continue;
            }

            stockInfoUuid = record[0];
            bookUuid = record[1];
            quantity = Integer.parseInt(record[2]);
            if(stockInfoUuid == null){
                stockInfoUuid =  UUID.randomUUID().toString();
            }

            Book targetBook = bookRepository.getByUuid(record[1]);
            if(targetBook == null){
                log.error("Can not find the book with uuid " + record[1]);
                continue;
            }
            stockInfoRepository.save(new StockInfo(stockInfoUuid,bookUuid,quantity));
        }
        // TODO : Delete the message
    }
}
