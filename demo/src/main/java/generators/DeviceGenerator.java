package generators;

import lombok.AllArgsConstructor;
import models.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

@AllArgsConstructor
public class DeviceGenerator {
    private Integer maxComps;
    private Integer deviceNum;
    private static SequenceGenerator seq = new SequenceGenerator();

    public static Component generateComponent(Integer id) {
        String tempStr = RandomStringUtils.randomAlphabetic(10);
        Component comp = new Component(id, tempStr, true);
        return comp;
    }

    public static Device generateDevice(Integer compNum) {
        HashMap<Integer, Component> components = new HashMap<>();
        Integer sequ = seq.getNext();
        for (int i = 1; i <= compNum; ++i) {
            Component comp = generateComponent(i);
            components.put(comp.getId(), comp);
        }

        return new Device(sequ, RandomStringUtils.randomAlphabetic(3), DeviceStatus.Normal, components);
    }


    public HashMap<Integer, Device> generateDevices() {
        Random r = new Random();
        HashMap<Integer, Device> devices = new HashMap<>();
        for (int i = 0; i < deviceNum; ++i) {
            Integer comps = r.nextInt(maxComps) + 1;
            Device dev = generateDevice(comps);

            devices.put(dev.getId(), dev);
        }
        return devices;
    }

}
