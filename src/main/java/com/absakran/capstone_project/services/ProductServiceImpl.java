package com.absakran.capstone_project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.absakran.capstone_project.entities.Product;
import com.absakran.capstone_project.repositories.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductDAO productDAO;

    @Override
    public void addProduct(Product product) {
            productDAO.addProduct(product);
    }

    @Override
    public void buyProduct(Product product, int quantity) {
        Product temp = product;
        System.out.println("buying product: " + temp.getName() + " with quantity: " + quantity);
        temp.setQuantity(temp.getQuantity()-quantity);
        productDAO.updateProduct(temp, product.getId());
    }

    @Override
    public List<Product> getAllProducts() {
        // TODO Auto-generated method stub
        return productDAO.getAllProducts();
    }

    @Override
    public Product getProductById(long id) {
        // TODO Auto-generated method stub
        return productDAO.getProductById(id);
    }

    @Override
    public List<Product> getProductsByKeyword(String keyword) {
        // TODO Auto-generated method stub
        return productDAO.getProductsByKeyword(keyword);
    }

    @Override
    public void returnProduct(long id, int quantity) {
        // TODO Auto-generated method stub
        Product temp = productDAO.getProductById(id);
        System.out.println("returning product: " + temp.getName() + " with quantity: " + quantity);
        System.out.println("current quantity: " + temp.getQuantity());
        System.out.println("new quantity: " + (temp.getQuantity() + quantity));
        temp.setQuantity(temp.getQuantity() + quantity);
        productDAO.updateProduct(temp, temp.getId());
    }






    public ProductServiceImpl(ProductDAO productDAO){
        this.productDAO=productDAO;
    }


}
