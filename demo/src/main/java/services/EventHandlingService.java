package services;

import events.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import models.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Queue;

@Data
@NoArgsConstructor
@Service
@Log
@AllArgsConstructor
public class EventHandlingService {
    private Queue<Event> events;
    private Map<Integer, Device> devices;

    @Autowired
    private Context context;

    public void handleEvents() {
        Integer al = events.size();
        Integer i = 0;
        context.setDevices(devices);
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