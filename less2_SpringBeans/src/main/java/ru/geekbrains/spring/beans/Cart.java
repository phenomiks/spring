package ru.geekbrains.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private final ProductRepository productRepository;
    private List<Product> products;

    @Autowired
    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
    }

    public void addProductToCart(long id) {
        products.add(productRepository.findProduct(id));
    }

    public void removeProductInTheCart(long id) {
        products.remove(productRepository.findProduct(id));
    }

    public void printCart() {
        if (products.isEmpty()) {
            System.out.println("Cart is empty");
        } else {
            System.out.println("Cart composition:");
            for (Product p : products) {
                System.out.println(p.toString());
            }
        }
    }
}
