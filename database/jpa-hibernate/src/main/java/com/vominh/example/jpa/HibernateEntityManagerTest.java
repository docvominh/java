package com.vominh.example.jpa;

import com.vominh.example.jpa.entity.DeviceWithVersionEntity;
import org.hibernate.jpa.HibernateEntityManager;

import javax.persistence.EntityManagerFactory;

public class HibernateEntityManagerTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = AppEntityManagerFactory.getEntityManagerFactory();
        var entityManager = emf.createEntityManager();
        HibernateEntityManager hibernateEntityManager = entityManager.unwrap(HibernateEntityManager.class);

        var deleteAllDevice = hibernateEntityManager.createQuery("delete from DeviceWithVersionEntity");

        try {
            hibernateEntityManager.getTransaction().begin();
            deleteAllDevice.executeUpdate();

            DeviceWithVersionEntity device1 = new DeviceWithVersionEntity();
            device1.setId(1);
            device1.setSerial(8888);
            device1.setName("Dell xps 88");

            DeviceWithVersionEntity device2 = new DeviceWithVersionEntity();
            device2.setId(2);
            device2.setSerial(9999);
            device2.setName("Dell xps 99");

            hibernateEntityManager.persist(device1);
            hibernateEntityManager.persist(device2);

            hibernateEntityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            hibernateEntityManager.getTransaction().rollback();
        } finally {
            hibernateEntityManager.close();
            emf.close();
        }
    }
}
