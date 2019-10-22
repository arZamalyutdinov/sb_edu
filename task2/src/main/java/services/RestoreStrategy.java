package services;

import dao.DeviceDao;
import lombok.AllArgsConstructor;
import models.Device;

import java.util.Map;


@AllArgsConstructor
public class RestoreStrategy implements EventHandlingStrategy {

    private Map<Integer, Device> devices;
    private Integer deviceId;
    private Integer compId;

    @Override
    public void execute() {
        Device device = this.devices.get(deviceId);
        device.changeComponentStatus(compId, true);
        updateDevice(device);
    }

    private void updateDevice(Device device) {
        DeviceDao db = new DeviceDao();
        db.update(device);
    }
}