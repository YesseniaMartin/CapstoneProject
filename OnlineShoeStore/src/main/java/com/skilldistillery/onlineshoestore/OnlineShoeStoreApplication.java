package com.skilldistillery.onlineshoestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.skilldistillery.jpaonlineshoestore")
@SpringBootApplication
public class OnlineShoeStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoeStoreApplication.class, args);
	}

}
