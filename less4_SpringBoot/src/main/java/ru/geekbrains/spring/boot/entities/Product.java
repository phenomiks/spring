package ru.geekbrains.spring.boot.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private double cost;

    @Override
    public String toString() {
        return String.format("Product [ id = %d, title = %s, cost = %.2f ]", id, title, cost);
    }
}
