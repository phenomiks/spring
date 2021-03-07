package ru.geekbrains.spring.mvc;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        products.add(new Product(1L, "TV", 499.90));
        products.add(new Product(2L, "Washer", 300.90));
        products.add(new Product(3L, "Fridge", 600.));
        products.add(new Product(4L, "Hoover", 49.90));
        products.add(new Product(5L, "PlayStation 5", 399.90));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public Product getProductById(long id) throws IllegalArgumentException {
        for (Product product : products) {
            if (id == product.getId()) {
                return product;
            }
        }
        throw new IllegalArgumentException("Product not found by id");
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProductById(long id) {
        products.remove(getProductById(id));
    }
}
