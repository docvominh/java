package com.vominh.example.jpa;

import com.vominh.example.jpa.entity.DeviceWithVersionEntity;

import javax.persistence.EntityManagerFactory;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class TestOptimisticLock {

    public static void main(String[] args) throws InterruptedException {
        DataUtils.setup();


        // open 50 session in 50 thread to update one record
        for (int i = 0; i < 20; i++) {
            CompletableFuture.runAsync(() -> {
                System.out.println(Thread.currentThread().getName());
                EntityManagerFactory emf = AppEntityManagerFactory.getEntityManagerFactory();
                var entityManager = emf.createEntityManager();
                try {

                    DeviceWithVersionEntity d = entityManager.find(DeviceWithVersionEntity.class, 1);
                    System.out.println(d.getName());
                    d.setName((new Random()).nextInt(500) + "");
                    System.out.println(d.getName());

                    entityManager.getTransaction().begin();
                    entityManager.merge(d);
                    entityManager.getTransaction().commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    entityManager.getTransaction().rollback();
                } finally {
                    entityManager.close();
                }
            });
        }


        Thread.sleep(10000);


    }

}
