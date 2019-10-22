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
public class RestoreStrategy implements EventHandlingStrategy {

    @Autowired
    private DeviceDaoImpl db;

    @Override
    public void execute(Map<Integer, Device> devices, Integer deviceId, Integer compId) {
        Device device = devices.get(deviceId);
        device.changeComponentStatus(compId, true);
        updateDevice(device);
    }

    private void updateDevice(Device device) {
        db.update(new DeviceDto(device));
    }
}