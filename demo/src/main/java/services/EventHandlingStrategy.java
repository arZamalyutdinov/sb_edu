package services;

import models.Device;

import java.util.Map;

public interface EventHandlingStrategy {
    void execute(Map<Integer, Device> devices, Integer deviceId, Integer compId);
}
