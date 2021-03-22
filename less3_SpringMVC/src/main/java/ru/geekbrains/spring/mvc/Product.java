package ru.geekbrains.spring.mvc;

public class Product {
    private long id;
    private String title;
    private double cost;

    public Product(long id, String title, double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }
}
