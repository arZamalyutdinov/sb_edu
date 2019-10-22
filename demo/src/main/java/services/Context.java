package services;

import events.ErrorEvent;
import events.Event;
import events.RestoreEvent;
import lombok.NoArgsConstructor;
import models.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@NoArgsConstructor
@Service
public class Context {

    @Autowired
    private ErrorStrategy errorStrategy;

    @Autowired
    private RestoreStrategy restoreStrategy;

    private EventHandlingStrategy strategy;
    private Map<Integer, Device> devices;
    private Event event;

    public void setDevices(Map<Integer, Device> devices) {
        this.devices = devices;
    }

    public void setStrategy(Event e) throws IllegalArgumentException {
        if (e instanceof ErrorEvent) {
            this.strategy = errorStrategy;
        } else if (e instanceof RestoreEvent) {
            this.strategy = restoreStrategy;
        } else {
            throw new IllegalArgumentException();
        }
        this.event = e;
    }

    public void executeStrategy() {
        this.strategy.execute(devices, event.getDeviceID(), event.getComponentId());
    }
}
