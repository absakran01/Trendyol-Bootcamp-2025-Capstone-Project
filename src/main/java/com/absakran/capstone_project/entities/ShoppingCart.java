package com.absakran.capstone_project.entities;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "abdulmajeed_alsakran_shoppingcart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int numberOfProducts;

    @Column
    private double totalBill;

    @ManyToAny()
    @JoinTable(name = "shopping_cart_products",
        joinColumns = {@JoinColumn(name = "shopping_cart_id")},
        inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> cart;

    // getters and setters
    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }
}
