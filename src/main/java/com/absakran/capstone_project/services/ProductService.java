package com.absakran.capstone_project.services;

import java.util.List;

import com.absakran.capstone_project.entities.Product;

public interface ProductService {
    List<Product> getAllProducts();

    void addProduct(Product product);

    void buyProduct(Product product, int quantity);

    void returnProduct(long id, int quantity);

    Product getProductById(long id);

    List<Product> getProductsByKeyword(String keyword);


}
