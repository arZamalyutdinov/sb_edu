package events;


import models.Device;

import java.util.HashMap;


public class RestoreEvent extends Event {
    public RestoreEvent(Integer deviceId, Integer componentId) {
        super(deviceId, componentId);
    }
}