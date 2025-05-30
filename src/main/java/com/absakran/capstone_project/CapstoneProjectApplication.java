package com.absakran.capstone_project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.absakran.capstone_project.entities.Product;
import com.absakran.capstone_project.entities.ShoppingCart;

@SpringBootApplication
public class CapstoneProjectApplication implements CommandLineRunner {

	

	public static void main(String[] args) {

		SpringApplication.run(CapstoneProjectApplication.class, args);
	}

	@Override
	public void run(String... args) {
		
	}
	// This method is used to sanitize the product data before adding it to the database

}
