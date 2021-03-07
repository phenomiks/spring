package ru.geekbrains.spring.mvc.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.mvc.Product;
import ru.geekbrains.spring.mvc.ProductRepository;

@Controller
@RequestMapping("/repository")
public class RepositoryController {
    private ProductRepository productRepository;

    @Autowired
    public RepositoryController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public String showProductRepository(Model model) {
        model.addAttribute("allProducts", productRepository.getAllProducts());
        return "repository";
    }

    @PostMapping("/add")
    public String addNewProduct(@RequestParam(defaultValue = "0") long id, @RequestParam(defaultValue = "null") String title, @RequestParam(defaultValue = "0") double cost) {
        if (id != 0 && cost != 0 && !title.equals("null")) {
            productRepository.addProduct(new Product(id, title, cost));
        }
        return "redirect:/repository/all";
    }

    @GetMapping("/remove/{id}")
    public String removeProductById(@PathVariable long id) {
        productRepository.removeProductById(id);
        return "redirect:/repository/all";
    }
}
