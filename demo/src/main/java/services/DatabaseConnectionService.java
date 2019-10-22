package services;

import dao.ComponentDaoImpl;
import dao.DeviceDaoImpl;
import dto.DeviceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DatabaseConnectionService {

    @Autowired
    DeviceDaoImpl deviceDao;

    @Autowired
    ComponentDaoImpl componentDao;

    public Map<Integer, DeviceDto> getDevices() {
        return devices;
    }

    public void setDevices(Map<Integer, DeviceDto> devices) {
        this.devices = devices;
    }

    private Map<Integer, DeviceDto> devices;

    public DeviceDto getDeviceById (Integer id) {
        return deviceDao.findDevice(id);
    }

    public void deleteDevice(DeviceDto device) {
        deviceDao.delete(device);
    }

    public void saveDevice(DeviceDto device) {
        deviceDao.save(device);
    }

    public void updateDevice(DeviceDto device) {
        deviceDao.update(device);
    }

    public Map<Integer, DeviceDto> getAllDevices() {
        return deviceDao.findAllDevices();
    }

}
