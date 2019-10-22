package generators;

import events.*;
import lombok.AllArgsConstructor;
import models.Component;
import models.Device;

import java.util.*;

@AllArgsConstructor
public class EventGenerator {
    private Map<Integer, Device> devices;

    private Event generateEvent(Integer type) throws RuntimeException {
        Random r = new Random();
        Integer devId = r.nextInt(devices.size()) + 1;
        Device dev = devices.get(devId);
        Integer compId = r.nextInt(dev.getCompNum()) + 1;
        EventFactory fact = new EventFactory();
        if (type == 2) {
            type = 1;
        }
        return fact.getEvent(type, devId, compId);
    }

    public Queue<Event> generateEvents(Integer eventNum) {
        Random r = new Random();
        ArrayDeque<Event> events = new ArrayDeque<>();
        for (int i = 0; i < eventNum; ++i) {
            Integer type = r.nextInt(3);
            try {
                events.addLast(generateEvent(type));
            } catch (RuntimeException e) {
                System.out.println(e);
                throw e;
            }
        }
        return events;
    }
}
