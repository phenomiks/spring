package ru.geekbrains.spring.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.boot.entities.Product;
import ru.geekbrains.spring.boot.services.ProductService;

@Controller
@RequestMapping(value = "/index")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAllProducts(Model model) {
        model.addAttribute("allProducts", productService.findAllProducts());
        return "index";
    }

    @PostMapping(value = "/add")
    public String addProduct(@RequestParam(defaultValue = "0") Long id,
                             @RequestParam(defaultValue = "") String title,
                             @RequestParam(defaultValue = "0") double cost) {
        if (id != 0 && !title.equals("") && cost != 0) {
            productService.addProduct(new Product(id, title, cost));
        }
        return "redirect:/index";
    }

    @GetMapping(value = "/remove/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/index";
    }
}
