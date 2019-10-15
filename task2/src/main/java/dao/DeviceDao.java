package dao;

import lombok.NoArgsConstructor;
import models.Device;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
public class DeviceDao {

    public void save(Device device) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(device);
        tx.commit();
        session.close();
    }

    public void update(Device device) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(device);
        tx.commit();
        session.close();
    }

    public void delete(Device device) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(device);
        tx.commit();
        session.close();
    }

    public Device findDevice(Integer id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Device.class, id);
    }

    public Map<Integer, Device> findAllDevices() {
        List<Device> lst = (List<Device>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("Select a from devices a").getResultList();
        return lst.stream().collect(Collectors.toMap(Device::getId, x -> x));
    }
}
