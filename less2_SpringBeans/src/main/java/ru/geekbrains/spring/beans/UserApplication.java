package ru.geekbrains.spring.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(UserApplication.class, args);
        Cart cart = context.getBean("cart", Cart.class);
        ProductRepository productRepository = context.getBean("productRepository", ProductRepository.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Это консольное приложение для управления Вашей корзиной. Для выхода введите \"exit\".\n" +
                "Приложение подерживает следующие команды:\n" +
                "VIEW - показ списка доступных товаров. Пример запроса - \"VIEW\";\n" +
                "ADD - добавление товара в корзину. Пример запроса - \"ADD id\", где id - номер товара в списке доступных товаров (см. VIEW);\n" +
                "DELETE - удаление товара из корзины. Пример запроса - \"DELETE id\", где id - номер товара в списке доступных товаров (см. VIEW);\n" +
                "SHOW - показ содержимого корзины. Пример запроса - \"SHOW\".");
        String line;
        while (!(line = reader.readLine()).equals("exit")) {
            String[] strings = line.split(" ");
            if (strings.length > 2) {
                System.out.println("Команда не распознана. Проверьте Ваш запрос");
                continue;
            }
            String command = strings[0].toUpperCase();
            if (command.equals("VIEW")) {
                productRepository.printProductRepository();
            } else if (command.equals(CommandCartList.ADD.toString())) {
                try {
                    long id = Long.parseLong(strings[1]);
                    cart.addProductToCart(id);
                } catch (NumberFormatException e) {
                    System.out.println("id не найден. Проверьте Ваш запрос");
                }
            } else if (command.equals(CommandCartList.DELETE.toString())) {
                try {
                    long id = Long.parseLong(strings[1]);
                    cart.removeProductInTheCart(id);
                } catch (NumberFormatException ignored) {
                    System.out.println("id не найден. Проверьте Ваш запрос");
                }
            } else if (command.equals(CommandCartList.SHOW.toString())) {
                cart.printCart();
            } else {
                System.out.println("Команда не распознана. Повторите Ваш запрос. Для выхода введите \"exit\".");
            }
        }

//        Cart cart2 = context.getBean("cart", Cart.class);
//        cart2.printCart();

        context.close();
    }
}
