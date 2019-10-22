package services;

import events.Event;
import models.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Queue;

@Service
public class ProcessService {


    @Autowired
    GenerationService generationService;

    @Autowired
    EventHandlingService eventHandlingService;

    public void process() {
        Map<Integer, Device> devices = generationService.generateDevices(10, 30);
//        System.out.println(devices);
        Queue<Event> events = generationService.generateEvents(200, devices);
        eventHandlingService.setEvents(events);
        eventHandlingService.setDevices(devices);
        eventHandlingService.setContext(new Context());
        eventHandlingService.handleEvents();
    }


}
