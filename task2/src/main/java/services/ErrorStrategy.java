package services;

import lombok.AllArgsConstructor;
import models.Device;
import java.util.Map;


@AllArgsConstructor
public class ErrorStrategy implements EventHandlingStrategy {

    private Map<Integer, Device> devices;
    private Integer deviceId;
    private Integer compId;

    @Override
    public void execute() {
        Device device = this.devices.get(deviceId);
        device.changeComponentStatus(compId, false);
    }
}
