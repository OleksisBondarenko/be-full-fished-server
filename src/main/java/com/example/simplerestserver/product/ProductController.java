package com.example.simplerestserver.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    ProductService productService = new ProductService();

    @GetMapping("/products")
    public List<Product> products() {
        List<Product> products = productService.products();

        return products;
    }
}
