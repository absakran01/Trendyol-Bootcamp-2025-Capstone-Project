package com.absakran.capstone_project.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.absakran.capstone_project.entities.Product;
import com.absakran.capstone_project.repositories.ProductDAO;
import com.absakran.capstone_project.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ProductController {
    
    private final ProductService productService;


    @GetMapping("/listproducts")
    public ResponseEntity<Object> listproducts() {
        try {

            return new ResponseEntity<>(productsAsString(productService.getAllProducts()), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        //TODO: process POST request
        try {
            productService.addProduct(sanitizedProduct(product));
            return new ResponseEntity<>("Product " + " added.\n" + productAsString(product), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addproducts")
    public ResponseEntity<String> postMethodName(@RequestBody List<Product> products) {
        // TODO Auto-generated method stub
        try {
            for (Product product : products) {
                productService.addProduct(sanitizedProduct(product));
            }
            return new ResponseEntity<>("Products added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }
    

    @GetMapping("/searchproducts")
    public ResponseEntity<Object> searchProductsByKeyword(@RequestParam String keyword) {
        try {
            return new ResponseEntity<>(productsAsString(productService.getProductsByKeyword(keyword)), HttpStatus.OK);
        } catch (Exception e) {
            // System.out.println(e);
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/searchproduct")
    public ResponseEntity<Object> searchProductById(@RequestParam long id) {
        try {
            return new ResponseEntity<>(productAsString(productService.getProductById(id)), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/showproduct")
    public ResponseEntity<Object> showProduct(@RequestParam long id) {
        try {
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
            
        }
    }
    


    
    

    // for safety
        public Product sanitizedProduct(Product product) {
        // TODO Auto-generated method stub
        if (product == null) {
            throw new IllegalArgumentException("product is null");
        }
        Product productDTO = new Product();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName() != null ? product.getName().trim() : null);
        productDTO.setDescription(product.getDescription() != null ? product.getDescription().trim() : null);
        productDTO.setPrice(product.getPrice());
        productDTO.setTax(product.getTax());
        productDTO.setQuantity(product.getQuantity());

        return productDTO;
    }

        String productAsString(Product product) {
        if (product == null) {
            return "{}";
        }
        return "\t{\n" +
                "\t\tproduct name: " + product.getName() + "\n" +
                "\t\tprice(not including tax): " + product.getPrice() + "\n" +
                "\t\tstock left: " + product.getQuantity() + "\n\t}";
    }


    String productsAsString(List<Product> products){
        List<String> producStrings=new ArrayList<>();
        String productString = "";
        

            for (int i =0; i<products.size(); i++) {

                productString =
                            "\n\t{\n" +
                    "\t\tproduct name: " +products.get(i).getName() + "\n" + 
                    "\t\tprice(not including tax): " +products.get(i).getPrice() + "\n" +
                    "\t\tstock left: " +products.get(i).getQuantity() + "\n" +
                    "\t}"
                    ;

                    if (i == products.size() - 1) {
                        productString += "\n";
                    }

            producStrings.add(productString);
            productString = ""; // Reset for the next product
            }

        return producStrings+"";
    }


    public ProductController(ProductService productService){
        this.productService=productService;
    }
}
