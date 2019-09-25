import java.util.HashSet;

public class csh extends device {
    public csh() {
    }

    public csh(Integer id, String name, deviceStatus status, HashSet<Component> components) {
        super(id, name, status, components);
    }
}
