package ru.geekbrains.spring.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.boot.entities.Product;
import ru.geekbrains.spring.boot.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAllProducts();
    }

    public Optional<Product> findProductById(Long id) {
        Optional<Product> product = productRepository.findProductById(id);
        return product;
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }
}
