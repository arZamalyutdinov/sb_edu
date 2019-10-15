package services;

import dao.DeviceDao;
import events.Event;
import generators.DeviceGenerator;
import generators.EventGenerator;
import models.Component;
import models.Device;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;

public class ProcessService {

    public void process() {
        // Генерация

        DeviceGenerator deviceGenerator = new DeviceGenerator(10, 10);
//        Device d = DeviceGenerator.generateDevice(5);
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//        Component comp = d.getComponents().get(1);
//        session.save(comp);
//        tx1.commit();
//        session.close();
        Map<Integer, Device> mp = deviceGenerator.generateDevices();
        DeviceDao db = new DeviceDao();

        for (Map.Entry<Integer, Device> dv : mp.entrySet()) {
            Device d = dv.getValue();
            db.save(d);
        }
        //
//        HashMap<Integer, Device> devices = deviceGenerator.generateDevices();
//        System.out.println(devices);
//        EventGenerator eventGenerator = new EventGenerator(devices);
//        Queue<Event> events = eventGenerator.generateEvents(200);
//        EventHandlingService eventHandle = new EventHandlingService(events, devices);
//        eventHandle.handleEvents();
//        System.out.println(devices);
        // Обработка событий
        // Обновить статус
        // Печать состояния
    }



}
