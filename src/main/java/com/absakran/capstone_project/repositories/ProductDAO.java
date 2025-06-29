package com.absakran.capstone_project.repositories;

import java.util.List;

import com.absakran.capstone_project.entities.Product;

public interface ProductDAO {
    List<Product> getAllProducts();

    void addProduct(Product product);

    Product getProductById(long id);

    List<Product> getProductsByKeyword(String keyword);

    void updateProduct(Product product, long id);
}
