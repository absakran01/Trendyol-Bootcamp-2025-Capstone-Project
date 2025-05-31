package com.absakran.capstone_project.services;

import com.absakran.capstone_project.entities.Product;
import com.absakran.capstone_project.entities.ShoppingCart;

public interface ShoppingCartService {
    void addToCart(Product product, int quantity);

    void removeFromCart(long id);
    
    void initCart();

    ShoppingCart getCart();

    double bill();

    void emptyCart();
}
