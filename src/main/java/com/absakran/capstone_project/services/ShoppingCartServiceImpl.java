package com.absakran.capstone_project.services;

import org.springframework.stereotype.Service;

import com.absakran.capstone_project.entities.Product;
import com.absakran.capstone_project.entities.ShoppingCart;
import com.absakran.capstone_project.entities.ShoppingCartItem;
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

            productService.buyProduct(product, quantity);

            Product temp = new Product();
            temp.setId(product.getId());
            temp.setName(product.getName());
            temp.setDescription(product.getDescription());
            temp.setPrice(product.getPrice());
            temp.setTax(product.getTax());
            temp.setQuantity(quantity);
            shoppingCartDAO.addToCart(temp);
            

        }
    }

    @Override
    public void removeFromCart(long id) {
        // TODO Auto-generated method stub
        ShoppingCartItem product = shoppingCartDAO.removeFromCart(id);
        System.out.println("inside ShoppingCartServiceImpl removing product: " + product.getProduct().getName() + " with quantity: " + product.getQuantity());
        productService.returnProduct(product.getProduct().getId(), product.getQuantity());
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
        for (int i = shoppingCart.getItems().size() - 1; i >= 0; i--) {
            ShoppingCartItem item = shoppingCart.getItems().get(i);
            total += (item.getProduct().getPrice() * item.getQuantity() * item.getProduct().getTax()) +
                      (item.getProduct().getPrice() * item.getQuantity());
        }
        for (int i = shoppingCart.getItems().size()-1; i > -1; i--) {
            shoppingCartDAO.removeFromCart(shoppingCart.getItems().get(i).getProduct().getId());
        }
        shoppingCartDAO.updateCart(shoppingCart);
        return total;
    }

    @Override
    public void emptyCart() {
        // TODO Auto-generated method stub
        ShoppingCart shoppingCart = shoppingCartDAO.getCart();
        for (int i = shoppingCart.getItems().size()-1; i > -1; i--) {
            removeFromCart(shoppingCart.getItems().get(i).getProduct().getId());
        }
    }




    // DI
    ShoppingCartServiceImpl(ShoppingCartDAO shoppingCartDAO, ProductService productService){
        this.shoppingCartDAO=shoppingCartDAO;
        this.productService=productService;
    }
}
