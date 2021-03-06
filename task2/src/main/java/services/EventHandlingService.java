package services;

import events.Event;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import models.Device;

import java.util.Map;
import java.util.Queue;

@Log
@AllArgsConstructor
public class EventHandlingService {
    private Queue<Event> events;
    private Map<Integer, Device> devices;

    public void handleEvents() {
        Integer al = events.size();
        Integer i = 0;
        Context context = new Context(devices);
        for (Event e : events) {
            try {
                context.setStrategy(e);
                context.executeStrategy();
            } catch (Throwable ex) {
                log.info(ex.toString());
                ++i;
            }
        }
        System.out.println("Successful event handling num: " + (al - i) + '\n'
                + "Unsuccessful event handling num: " + i);
    }

}