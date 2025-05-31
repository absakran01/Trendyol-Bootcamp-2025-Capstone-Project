package com.absakran.capstone_project.entities;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
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



    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<ShoppingCartItem> items;

    // getters and setters
    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        String string =  "ShoppingCart [id=" + id + "]";
        for (ShoppingCartItem item : items) {
            string += ", item=" + item.getProduct().getName() + " (quantity: " + item.getQuantity() + ")";
        }
        return string;
    }

    // public void setId(int i) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setId'");
    // }
}
