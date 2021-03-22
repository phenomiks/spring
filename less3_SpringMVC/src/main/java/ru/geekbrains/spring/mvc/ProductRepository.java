package ru.geekbrains.spring.mvc;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public Optional<Product> getProductById(long id) throws IllegalArgumentException {
        Optional<Product> product = Optional.empty();
        for (Product p : products) {
            if (id == p.getId()) {
                product = Optional.of(p);
            }
        }
        return product;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProductById(long id) {
        Optional<Product> product = getProductById(id);
        product.ifPresent(p -> products.remove(p));
    }
}
