package com.absakran.capstone_project.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.absakran.capstone_project.entities.Product;
import com.absakran.capstone_project.repositories.ProductDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ProductController {
    
    private final ProductDAO productDAO;


    @GetMapping("/listproducts")
    public List<Product> listproducts() {
        return productDAO.getAllProducts();
    }

    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct(@RequestBody(required = false) Product product) {
        //TODO: process POST request
        if (product == null) {
            return new ResponseEntity<String>("please provide a product", HttpStatus.BAD_REQUEST);
        }
        else
        productDAO.addProduct(product);
        return new ResponseEntity<String>("succes!", HttpStatus.CREATED);
    }
    



    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    
}
