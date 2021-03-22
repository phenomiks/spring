package ru.geekbrains.hibernate;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.hibernate.entity.Product;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductDao {
    private static EntityManagerFactory factory;
    private static EntityManager em;

    public static void init() {
        factory = new Configuration()
                .configure("hibernate.config.xml")
                .buildSessionFactory();
        try {
            Path pathToFile = Paths.get(".\\less5_Hibernate1\\src\\main\\resources\\creatingTablesScript.sql");
            String sql = Files.lines(pathToFile).collect(Collectors.joining(" "));
            em = factory.createEntityManager();
            em.getTransaction().begin();
            em.createNativeQuery(sql).executeUpdate();
            em.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static Optional<Product> findById(Long id) {
        em = factory.createEntityManager();
        Optional<Product> product = Optional.empty();
        try {
            product = Optional.ofNullable(em.createNamedQuery("Product.findById", Product.class)
                    .setParameter("id", id).getSingleResult());
        } catch (NoResultException e) {
            System.out.println("Product not found");
        } finally {
            em.close();
        }
        return product;
    }

    public static List<Product> findAll() {
        em = factory.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Product p");
        List<Product> productList = (List<Product>) query.getResultList();
        em.close();
        return productList;
    }

    public static void deleteById(Long id) {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        try {
            Product product = em.find(Product.class, id);
            em.remove(product);
            em.getTransaction().commit();
            System.out.println("DELETE: " + product.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Not deleted. There is no product with this ID");
        } finally {
            em.close();
        }
    }

    public static void saveOrUpdate(Product product) {
        Optional<Product> optional = findById(product.getId());
        em = factory.createEntityManager();
        em.getTransaction().begin();
        if (optional.isPresent()) {
            Product foundProduct = optional.get();
            foundProduct.setTitle(product.getTitle());
            foundProduct.setPrice(product.getPrice());
            em.merge(foundProduct);
        } else {
            em.persist(product);
        }
        em.getTransaction().commit();
        em.close();
    }

    public static void main(String[] args) {
        try {
            init();
            Optional<Product> product1 = findById(1L);
            product1.ifPresent(p -> System.out.println(p.toString()));

            List<Product> productList = findAll();
            productList.forEach(p -> System.out.println(p.toString()));

            deleteById(3L);

            Product product = new Product();
//            product.setId(4L);
            product.setTitle("orange");
            product.setPrice(9);
            saveOrUpdate(product);
            findAll();
        } finally {
            factory.close();
        }
    }
}
