package com.absakran.capstone_project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.absakran.capstone_project.entities.Product;
import com.absakran.capstone_project.entities.ShoppingCart;
import com.absakran.capstone_project.entities.ShoppingCartItem;
import com.absakran.capstone_project.services.ProductService;
import com.absakran.capstone_project.services.ProductServiceImpl;
import jakarta.persistence.EntityManager;

@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDAO{

    private ProductService productService;
    private EntityManager em;

    //cont
    @Override
    @Transactional
    public void addToCart(Product product) {
        // TODO Auto-generated method stub
        ShoppingCart shoppingCart = em.find(ShoppingCart.class, 1);
        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setProduct(product);
        cartItem.setCart(shoppingCart);
        cartItem.setQuantity(product.getQuantity());
        em.persist(cartItem);  
        em.merge(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCartItem removeFromCart(long id) {
        // TODO Auto-generated method stub
        ShoppingCart shoppingCart = em.find(ShoppingCart.class, 1);
        ShoppingCartItem removedItem = shoppingCart.getItems().stream()
            .filter(item -> item.getProduct().getId() == id)
            .findFirst().orElseThrow(() -> new IllegalArgumentException("Product not found in cart"));

        em.remove(removedItem);
        shoppingCart.getItems().remove(removedItem);
        System.out.println("inside ShoppingCartDAOImpl removing product: " + removedItem.getProduct().getName() + " with quantity: " + removedItem.getQuantity());
        em.persist(shoppingCart);
        return removedItem;
    }

    @Override
    @Transactional
    public void initCart() {
        // TODO Auto-generated method stub
        if (em.find(ShoppingCart.class, 1) != null) {
            em.remove(em.find(ShoppingCart.class, 1));
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        em.persist(shoppingCart);
    }


    @Override
    @Transactional
    public void updateCart(ShoppingCart shoppingCart) {
        // TODO Auto-generated method stub
        em.merge(shoppingCart);
    }

    @Override
    public ShoppingCart getCart() {
        // TODO Auto-generated method stub
        return em.find(ShoppingCart.class, 1);
    }







    // DI
    ShoppingCartDAOImpl(EntityManager em, ProductService productService){
        this.em = em;
        this.productService = productService;
    }

    
}
