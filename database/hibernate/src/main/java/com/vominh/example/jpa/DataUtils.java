package com.vominh.example.jpa;

import com.vominh.example.jpa.entity.DeviceWithVersionEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

class DataUtils {
    public static void setup() {
        SessionFactory sessionFactory = AppSessionFactory.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query deleteAllDevice = session.createQuery("delete from DeviceWithVersionEntity");

        try {
            session.beginTransaction();
            deleteAllDevice.executeUpdate();

            DeviceWithVersionEntity device1 = new DeviceWithVersionEntity();
            device1.setId(1);
            device1.setSerial(8888);
            device1.setName("Dell xps 88");

            DeviceWithVersionEntity device2 = new DeviceWithVersionEntity();
            device2.setId(2);
            device2.setSerial(9999);
            device2.setName("Dell xps 99");

            session.save(device1);
            session.save(device2);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
