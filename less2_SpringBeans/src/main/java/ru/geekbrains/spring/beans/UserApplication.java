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
        long maxId = productRepository.getMaxId();
        while (!(line = reader.readLine()).equals("exit")) {
            String[] strings = line.split(" ");
            if (strings.length > 2) {
                System.out.println("Команда не распознана. Проверьте Ваш запрос");
                continue;
            }
            if (strings[0].toUpperCase().equals("VIEW")) {
                productRepository.printProductRepository();
            } else if (strings[0].toUpperCase().equals(CommandCartList.ADD.toString())) {
                try {
                    cart.addProductToCart(checkId(strings[1], maxId));
                    System.out.println("Товар добавлен в корзину");
                } catch (NumberFormatException ignored) {
                }
            } else if (strings[0].toUpperCase().equals(CommandCartList.DELETE.toString())) {
                try {
                    cart.removeProductInTheCart(checkId(strings[1], maxId));
                    System.out.println("Товар удален из корзины");
                } catch (NumberFormatException ignored) {
                }
            } else if (strings[0].toUpperCase().equals(CommandCartList.SHOW.toString())) {
                cart.printCart();
            } else {
                System.out.println("Команда не распознана. Повторите Ваш запрос. Для выхода введите \"exit\".");
            }
        }

//        Cart cart2 = context.getBean("cart", Cart.class);
//        cart2.printCart();

        context.close();
    }

    private static long checkId(String str, long maxId) throws NumberFormatException {
        long id = 0;
        try {
            id = Long.parseLong(str);
            if (id < 1 || id > maxId) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("id не найден. Проверьте Ваш запрос");
            throw new NumberFormatException();
        }
        return id;
    }
}
