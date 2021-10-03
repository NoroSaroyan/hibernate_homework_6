package ru.gb.dao;

import ru.gb.DBConnection;
import ru.gb.entity.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class CustomerDao {
    private final DBConnection connection;
    private final EntityManagerFactory factory;

    public CustomerDao(DBConnection connection) {
        this.connection = connection;
        factory = connection.getFactory();
    }

    public Optional<Customer> findById(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            return Optional.of(entityManager.find(Customer.class, id));
        } finally {
            entityManager.close();
        }
    }

    public List<Customer> findAll() {
        EntityManager entityManager = factory.createEntityManager();
        try {
            return entityManager
                    .createQuery("select c from Customer c", Customer.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Optional<Customer> saveOrUpdate(Customer customer) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return Optional.of(customer);
    }

    public void deleteById(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Customer.class, id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
