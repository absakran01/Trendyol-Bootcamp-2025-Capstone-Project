package com.absakran.capstone_project.services;

import org.springframework.stereotype.Service;

import com.absakran.capstone_project.entities.Product;
import com.absakran.capstone_project.entities.ShoppingCart;
import com.absakran.capstone_project.repositories.ShoppingCartDAO;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    private ShoppingCartDAO shoppingCartDAO;
    private ProductService productService;

    //cont
    @Override
    public void addToCart(Product product, int quantity) {
        // TODO Auto-generated method stub
        if(quantity>product.getQuantity()){
            throw new IllegalArgumentException("quantity exceeds available stock");
        } else {

            shoppingCartDAO.addToCart(product);
            productService.buyProduct(product, quantity);
            

        }
    }

    @Override
    public void removeFromCart(Product product) {
        // TODO Auto-generated method stub
        shoppingCartDAO.removeFromCart(product);
    }

    @Override
    public void initCart() {
        // TODO Auto-generated method stub
        shoppingCartDAO.initCart();
    }

    @Override
    public ShoppingCart getCart() {
        // TODO Auto-generated method stub
        return shoppingCartDAO.getCart();
    }

        @Override
    public double bill() {
        // TODO Auto-generated method stub
        ShoppingCart shoppingCart = shoppingCartDAO.getCart();
        double total = 0;
        for (Product product : shoppingCart.getCart()) {
            total += (product.getPrice() * product.getQuantity() * product.getTax()) +
                      (product.getPrice() * product.getQuantity());
        }
        return total;
    }




    // DI
    ShoppingCartServiceImpl(ShoppingCartDAO shoppingCartDAO, ProductService productService){
        this.shoppingCartDAO=shoppingCartDAO;
        this.productService=productService;
    }
}
