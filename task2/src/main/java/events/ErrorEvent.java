package events;


import models.Device;
import java.util.HashMap;


public class ErrorEvent extends Event {
    public ErrorEvent(Integer deviceId, Integer componentId) {
        super(deviceId, componentId);
    }
}
