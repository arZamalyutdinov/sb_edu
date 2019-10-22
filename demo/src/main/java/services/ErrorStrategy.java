package services;

import dao.DeviceDaoImpl;
import dto.DeviceDto;
import lombok.AllArgsConstructor;
import models.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class ErrorStrategy implements EventHandlingStrategy {

    @Autowired
    private DeviceDaoImpl db;

    @Override
    public void execute(Map<Integer, Device> devices, Integer deviceId, Integer compId) {
        Device device = devices.get(deviceId);
        device.changeComponentStatus(compId, false);
        updateDevice(device);
    }

    private void updateDevice(Device device) {
        db.update(new DeviceDto(device));
    }
}
