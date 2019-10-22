package services;

import dao.DeviceDaoImpl;
import dto.DeviceDto;
import lombok.AllArgsConstructor;
import models.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.SpringContext;

import java.util.Map;

@AllArgsConstructor
public class ErrorStrategy implements EventHandlingStrategy {

    private DeviceDaoImpl db;

    public ErrorStrategy() {
        this.db = SpringContext.getBean(DeviceDaoImpl.class);
    }

    @Override
    public void execute(Map<Integer, Device> devices, Integer deviceId, Integer compId) {
        Device device = devices.get(deviceId);
        device.changeComponentStatus(compId, false);
        updateDevice(device);
    }

    private void updateDevice(Device device) {

        System.out.println("1");
        db.update(new DeviceDto(device));
    }
}
