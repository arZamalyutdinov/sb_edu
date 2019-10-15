package services;

import events.ErrorEvent;
import events.Event;
import events.RestoreEvent;
import models.Device;

import java.util.Map;


public class Context {
    private EventHandlingStrategy strategy;
    private Map<Integer, Device> devices;

    public Context(Map<Integer, Device> devices) {
        this.devices = devices;
    }

    public void setStrategy(Event e) throws IllegalArgumentException {
        if (e instanceof ErrorEvent) {
            this.strategy = new ErrorStrategy(devices, e.getDeviceID(), e.getComponentId());
        } else if (e instanceof RestoreEvent) {
            this.strategy = new RestoreStrategy(devices, e.getDeviceID(), e.getComponentId());
        } else {
            throw new IllegalArgumentException();
        }
    }
    public void executeStrategy() {
        this.strategy.execute();
    }
}
