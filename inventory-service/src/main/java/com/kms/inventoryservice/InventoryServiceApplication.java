package com.kms.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
//		System.setProperty("restart.include.dynamodb", "/dynamodb-[\\\\w\\\\d-\\.]+\\.jar");
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
