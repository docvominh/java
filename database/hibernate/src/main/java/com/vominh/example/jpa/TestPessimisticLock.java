package com.vominh.example.jpa;

import com.vominh.example.jpa.entity.DeviceWithVersionEntity;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.SessionFactory;

import java.util.concurrent.CompletableFuture;

public class TestPessimisticLock {


    public static void main(String[] args) {
        DataUtils.setup();
        SessionFactory sessionFactory = AppSessionFactory.buildSessionFactory();
        try {

            CompletableFuture.runAsync(() -> {
                System.out.println("Thread 1, load and lock record");
                var session1 = sessionFactory.openSession();
                var lockOptions = new LockOptions(LockMode.PESSIMISTIC_READ);

                // Search and lock record, prevent update
                session1.getTransaction().begin();
                DeviceWithVersionEntity d1 = (DeviceWithVersionEntity) session1.load(DeviceWithVersionEntity.class, 1,lockOptions);
                try {
                    // Delay close session1 to make session2 wait to commit
                    Thread.sleep(10000);
                    session1.getTransaction().commit();
                    session1.close();
                    System.out.println("Session 1 closed");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread 2, load and update record");
                // Try to update locking record
                var session2 = sessionFactory.openSession();
                DeviceWithVersionEntity d2 = (DeviceWithVersionEntity) session2.load(DeviceWithVersionEntity.class, 1);
                System.out.printf("Still available to read: %s%n", d2.getName());
                session2.close();
                System.out.println("Session 2 closed");
                try {
                    d2.setName("new name");
                    var session3 = sessionFactory.openSession();
                    System.out.println("Session 3 start");
                    session3.getTransaction().begin();
                    System.out.println("Session 3 update entity");
                    session3.update(d2);
                    session3.getTransaction().commit(); // wait if session1 not close
                    session3.close();
                    System.out.println("Session 3 closed");
                } catch (Exception e) {
                    System.out.println(e);
                    e.printStackTrace();
                }
            });

            // Wait for thread 1,2 finish
            Thread.sleep(20000);
        } catch (Exception e) {
            System.err.println(e);
        }

        sessionFactory.close();
        System.out.println("EXIT");
    }
}
