package ru.gb.dao;

import org.springframework.stereotype.Repository;
import ru.gb.DBConnection;
import ru.gb.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao {

    private final DBConnection connection;
    private final EntityManagerFactory factory;

    public ProductDao(DBConnection connection) {
        this.connection = connection;
        factory = connection.getFactory();
    }

    public Optional<Product> findById(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            return Optional.of(entityManager.find(Product.class, id));
        } finally {
            entityManager.close();
        }
    }

    public List<Product> findAll() {
        EntityManager entityManager = factory.createEntityManager();
        try {
            return entityManager
                    .createQuery("select p from Product p", Product.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Optional<Product> saveOrUpdate(Product product) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return Optional.of(product);
    }

    public void deleteById(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Product.class, id));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

}