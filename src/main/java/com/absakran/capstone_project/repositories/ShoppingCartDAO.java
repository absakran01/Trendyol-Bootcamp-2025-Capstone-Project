package com.absakran.capstone_project.repositories;

import com.absakran.capstone_project.entities.Product;

import com.absakran.capstone_project.entities.ShoppingCart;

public interface ShoppingCartDAO {
    public void addToCart(Product product);

    public void initCart();

    public ShoppingCart getCart();

    public void removeFromCart(Product product);

}
