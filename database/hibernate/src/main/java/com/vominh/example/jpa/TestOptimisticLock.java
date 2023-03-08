package com.vominh.example.jpa;

import com.vominh.example.jpa.entity.DeviceWithVersionEntity;
import org.hibernate.SessionFactory;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class TestOptimisticLock {

    public static void main(String[] args) {
        DataUtils.setup();
        SessionFactory sessionFactory = AppSessionFactory.buildSessionFactory();

        // open 50 session in 50 thread to update one record
        for (int i = 0; i < 50; i++) {
            CompletableFuture.runAsync(() -> {
                var s = sessionFactory.openSession();
                try {
                    s.beginTransaction();

                    DeviceWithVersionEntity d = (DeviceWithVersionEntity) s.load(DeviceWithVersionEntity.class, 1);
                    d.setName((new Random()).nextInt(500) + "");
                    s.save(d);

                    s.getTransaction().commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    s.getTransaction().rollback();
                } finally {
                    s.close();
                }
            });
        }


    }

}
