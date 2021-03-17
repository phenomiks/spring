package ru.geekbrains.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Product> product = productRepository.findProduct(id);
        if (product.isPresent()) {
            products.add(product.get());
            System.out.println("Товар добавлен в корзину");
        } else {
            System.out.println("Товар с указанным id не найден");
        }
    }

    public void removeProductInTheCart(long id) {
        Optional<Product> product = products.stream().filter(p -> p.getId() == id).findFirst();
        if (product.isPresent()) {
            products.remove(product.get());
            System.out.println("Товар удален из корзины");
        } else {
            System.out.println("Товар с указанным id не найден");
        }
    }

    public void printCart() {
        if (products.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println("Состав корзины:");
            for (Product p : products) {
                System.out.println(p.toString());
            }
        }
    }
}
