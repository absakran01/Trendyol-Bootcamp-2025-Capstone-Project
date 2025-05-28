package com.absakran.capstone_project.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ShoppingCartController {

    // basic testing
    @GetMapping("/test")
    public String test() {
        return "working";
    }

    
    
}
