package ru.geekbrains.spring.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.boot.entities.Product;
import ru.geekbrains.spring.boot.services.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/json")
public class JsonController {
    private ProductService productService;

    @Autowired
    public JsonController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> showJsonProductRepository() {
        return productService.findAllProducts();
    }
}
