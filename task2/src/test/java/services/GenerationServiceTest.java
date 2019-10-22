package services;
import dao.DeviceDao;
import models.Device;
import org.junit.Test;

import java.util.Map;

public class GenerationServiceTest {

    @Test
    public void generateDevices() {
        DeviceDao db = new DeviceDao();
        GenerationService generationService = new GenerationService();
        Map<Integer, Device> devces = generationService.generateDevices(10, 30);
        Map<Integer, Device> mp = db.findAllDevices();
        assert mp.equals(devces);
    }
}
