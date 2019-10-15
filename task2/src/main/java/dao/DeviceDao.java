package dao;

import lombok.NoArgsConstructor;
import models.Device;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
public class DeviceDao {

    public void save(Device device) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(device);
        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public void update(Device device) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(device);
        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public void delete(Device device) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(device);
        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public Device findDevice(Integer id) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Device device = session.get(Device.class, id);
        session.close();
        sessionFactory.close();
        return device;
    }

    public Map<Integer, Device> findAllDevices() {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        List<Device> lst = (List<Device>) sessionFactory
                .openSession()
                .createSQLQuery("Select * from devices")
                .addEntity(Device.class)
                .list();
        sessionFactory.close();
        return lst.stream().collect(Collectors.toMap(Device::getId, x -> x));
    }
}
