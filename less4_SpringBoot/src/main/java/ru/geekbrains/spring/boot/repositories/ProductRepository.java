package ru.geekbrains.spring.boot.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.boot.entities.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public Product findProductById(Long id) throws IllegalArgumentException {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new IllegalArgumentException("Product not found");
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProductById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
//        for (Product product : products) {
//            if (product.getId().equals(id)) {
//                products.remove(product);
//                return;
//            }
//        }
    }
}
