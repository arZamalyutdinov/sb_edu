import models.Component;

import java.util.HashSet;

public class atm extends device {
    public atm(Integer id, String name, deviceStatus status, HashSet<Component> components) {
        super(id, name, status, components);
    }
}
