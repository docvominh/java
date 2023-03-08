package com.vominh.example.jpa;

import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import java.util.Collections;

public class AppEntityManagerFactory {

    private static EntityManagerFactory entityManagerFactory;

    static {
        try {
            PersistenceProvider provider = new HibernatePersistenceProvider();
            entityManagerFactory = provider.createEntityManagerFactory("jdbc", Collections.emptyMap());
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
