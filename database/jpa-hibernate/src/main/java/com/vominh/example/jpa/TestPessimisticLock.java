package com.vominh.example.jpa;

import com.vominh.example.jpa.entity.DeviceWithVersionEntity;

import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import java.util.concurrent.CompletableFuture;

public class TestPessimisticLock {


    public static void main(String[] args) {
        DataUtils.setup();
        EntityManagerFactory emf = AppEntityManagerFactory.getEntityManagerFactory();
        try {
            CompletableFuture.runAsync(() -> {
                System.out.println("Thread 1, load and lock record");
                var entityManager = emf.createEntityManager();
                // Search and lock record, prevent update
                entityManager.getTransaction().begin();
                DeviceWithVersionEntity d = entityManager.find(DeviceWithVersionEntity.class, 1, LockModeType.PESSIMISTIC_READ);
                try {
                    // Delay close session1 to make session2 wait to commit
                    Thread.sleep(10000);
                    entityManager.getTransaction().commit();
                    entityManager.close();
                    System.out.println("Session 1 closed");
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            });

            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread 2, load and update record");
                // Try to update locking record
                var entityManager2 = emf.createEntityManager();
                DeviceWithVersionEntity d2 = entityManager2.find(DeviceWithVersionEntity.class, 1);
                System.out.printf("Still available to read: %s%n", d2.getName());
                entityManager2.close();
                System.out.println("Session 2 closed");

                try {
                    d2.setName("new name");
                    var entityManager3 = emf.createEntityManager();
                    System.out.println("Session 3 start");
                    entityManager3.getTransaction().begin();
                    System.out.println("Session 3 update entity");
                    entityManager3.merge(d2);
                    entityManager3.getTransaction().commit(); // wait if session1 not close
                    entityManager3.close();
                    System.out.println("Session 3 closed");
                } catch (Exception e) {
                    System.err.println(e);
                    e.printStackTrace();
                }
            });

            // Wait for thread 1,2 finish
            Thread.sleep(20000);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
