package com.absakran.capstone_project.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.absakran.capstone_project.entities.Product;
import com.absakran.capstone_project.services.ProductService;
import com.absakran.capstone_project.services.ShoppingCartService;

import java.util.Dictionary;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private ProductService productService;



    // basic testing
    @GetMapping("/test")
    public String test() {
        return "working";
    }

    @GetMapping("/init")
    public void initCart() {
        shoppingCartService.initCart();
    }
    

    
    @PostMapping("/addtocart")
    public ResponseEntity<Object> addToCart(@RequestParam(required = false) long id, @RequestParam(required = false) int quantity){
        if(id==0)
            return new ResponseEntity<>("please provide product ID", HttpStatus.BAD_REQUEST);


        else{
            Product product = productService.getProductById(id);
            if(product==null)
                return new ResponseEntity<>("product not found", HttpStatus.NOT_FOUND);



            else{
                try {
                    if(quantity <= 0)
                        return new ResponseEntity<>("quantity must be greater than 0", HttpStatus.BAD_REQUEST);


                    shoppingCartService.addToCart(product, quantity);
                    return new ResponseEntity<>("product added to cart", HttpStatus.OK);

                }  catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e);
                        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
                    }
            }
        }   
    }

    @PostMapping("/removefromcart")
    public ResponseEntity<Object> removeFromCart(@RequestParam(required = false) long id){
        if(id==0)
            return new ResponseEntity<>("please provide product ID", HttpStatus.BAD_REQUEST);
        else{
            try{
                shoppingCartService.removeFromCart(id);
                return new ResponseEntity<>("product removed from cart", HttpStatus.OK);
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
                return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
            }
        }

    }

    @GetMapping("/showcart")
    public ResponseEntity<Object> showCart() {
        try {
            return new ResponseEntity<>(shoppingCartService.getCart().toString(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/emptycart")
    public ResponseEntity<Object> emptyCart() {
        shoppingCartService.emptyCart();
        return new ResponseEntity<>("Cart emptied", HttpStatus.OK);
    }

    @GetMapping("/pay")
    public ResponseEntity<Object> pay() {
        // TODO: implement payment processing
        return new ResponseEntity<>("Payment of " + shoppingCartService.bill() + " turkish lira successful", HttpStatus.OK);
    }




    // DI
    ShoppingCartController(ShoppingCartService service,ProductService productService){
        this.productService=productService;
        shoppingCartService=service;
    }
}
