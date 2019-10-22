package services;

import dao.DeviceDao;
import events.Event;
import generators.DeviceGenerator;
import generators.EventGenerator;
import models.Device;

import java.util.Map;
import java.util.Queue;

public class GenerationService {

    public Map<Integer, Device> generateDevices(Integer maxComp, Integer deviceNum) {
        DeviceDao db = new DeviceDao();
        Map<Integer, Device> mp = db.findAllDevices();
        if (mp.isEmpty()) {
            DeviceGenerator deviceGenerator = new DeviceGenerator(maxComp, deviceNum);
            mp = deviceGenerator.generateDevices();
            for (Map.Entry<Integer, Device> entry : mp.entrySet()) {
                Device device = entry.getValue();
                db.save(device);
            }
        }
        return mp;
    }

    public Queue<Event> generateEvents(Integer eventNum, Map<Integer, Device> devices) {
        EventGenerator eventGenerator = new EventGenerator(devices);
        return eventGenerator.generateEvents(eventNum);
    }
}
