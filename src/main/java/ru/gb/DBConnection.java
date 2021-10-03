package ru.gb;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import ru.gb.entity.Product;

import javax.persistence.EntityManagerFactory;

@Service
public class DBConnection {
    private final EntityManagerFactory factory;

    public DBConnection(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

}