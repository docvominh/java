package com.vominh.example.jpa;

import com.vominh.example.jpa.entity.DeviceWithVersionEntity;

import javax.persistence.EntityManagerFactory;

class DataUtils {
    public static void setup() {
        EntityManagerFactory emf = AppEntityManagerFactory.getEntityManagerFactory();
        var entityManager = emf.createEntityManager();
        var deleteAllDevice = entityManager.createQuery("delete from DeviceWithVersionEntity");

        try {
            entityManager.getTransaction().begin();
            deleteAllDevice.executeUpdate();

            DeviceWithVersionEntity device1 = new DeviceWithVersionEntity();
            device1.setId(1);
            device1.setSerial(8888);
            device1.setName("Dell xps 88");

            DeviceWithVersionEntity device2 = new DeviceWithVersionEntity();
            device2.setId(2);
            device2.setSerial(9999);
            device2.setName("Dell xps 99");

            entityManager.persist(device1);
            entityManager.persist(device2);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            emf.close();
        }
    }
}
