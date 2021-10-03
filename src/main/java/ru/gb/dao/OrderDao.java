package ru.gb.dao;


import ru.gb.entity.Customer;
import ru.gb.entity.Product;
import ru.gb.DBConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public class OrderDao {
    private final DBConnection connection;
    private final EntityManagerFactory factory;

    public OrderDao(DBConnection connection) {
        this.connection = connection;
        factory = connection.getFactory();
    }

    public void addProductToCustomer(Customer customer, Product product) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            customer.addProductToCustomer(product);
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public List<Product> findByCustomerId(Long customerId) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            return entityManager
                    .createQuery("select c from Customer c", Product.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<Customer> findByProductId(Long productId) {

        return null;
    }

    public Integer getPriceOrder(Customer customer) {
        List<Product> products = findByCustomerId(customer.getId());
        Integer resultPrice = 0;
        for(Product p : products) {
            resultPrice += p.getPrice();
        }
        return resultPrice;
    }

}