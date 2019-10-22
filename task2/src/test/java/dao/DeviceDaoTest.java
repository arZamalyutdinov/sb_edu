package dao;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
public class DeviceDaoTest {
    @Test
    public void findAllDevices() {
        DeviceDao db = new DeviceDao();
        assert !db.findAllDevices().isEmpty();
    }
}
