package services;

import dto.DeviceDto;
import events.Event;
import generators.DeviceGenerator;
import generators.EventGenerator;
import models.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@Service
public class GenerationService {

    @Autowired
    DatabaseConnectionService databaseConnectionService;

    public Map<Integer, Device> generateDevices(Integer maxComp, Integer deviceNum) {
        Map<Integer, DeviceDto> mp = databaseConnectionService.getAllDevices();
        if (mp.isEmpty()) {
            DeviceGenerator deviceGenerator = new DeviceGenerator(maxComp, deviceNum);
            Map<Integer, Device> mp1 = deviceGenerator.generateDevices();
            Map<Integer, DeviceDto> mpDto = new HashMap<>();
            for (Map.Entry<Integer, Device> entry : mp1.entrySet()) {
                Device device = entry.getValue();
                mp.put(device.getId(), new DeviceDto(device));
                databaseConnectionService.saveDevice(new DeviceDto(device));
            }
        }
        Map<Integer, Device> mp1 = new HashMap<>();
        for (Map.Entry<Integer, DeviceDto> entry : mp.entrySet()) {
            DeviceDto device = entry.getValue();
            mp1.put(device.getId(), new Device(device));
        }
        return mp1;
    }
    public Queue<Event> generateEvents(Integer eventNum, Map<Integer, Device> devices) {
        EventGenerator eventGenerator = new EventGenerator(devices);
        return eventGenerator.generateEvents(eventNum);
    }
}
