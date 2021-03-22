package ru.geekbrains.spring.boot.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.boot.entities.Product;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ProductRepository {
    List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "TV1", 599.90),
                new Product(2L, "TV2", 499.90),
                new Product(3L, "TV3", 699.90),
                new Product(4L, "TV4", 999.90),
                new Product(5L, "TV5", 1999.90)
        ));
    }

    public List<Product> findAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> findProductById(Long id) throws IllegalArgumentException {
        Optional<Product> product = Optional.empty();
        for (Product p : products) {
            if (p.getId().equals(id)) {
                product = Optional.of(p);
            }
        }
        return product;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProductById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
