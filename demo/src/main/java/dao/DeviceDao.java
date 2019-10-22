package dao;


import dto.DeviceDto;

import java.util.Map;

public interface DeviceDao {
    void save(DeviceDto device);
    void update(DeviceDto device);
    void delete(DeviceDto device);
    DeviceDto findDevice (Integer id);
    Map<Integer, DeviceDto> findAllDevices();
}
