import java.util.HashSet;

abstract class device {
    private Integer id;
    private String name;
    private deviceStatus status;
    private HashSet<Component> components;

    public device() {
    }

    public device(Integer id, String name, deviceStatus status, HashSet<Component> components) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.components = components;
    }

    public deviceStatus getStatus() {
        return status;
    }

    public void setStatus(deviceStatus status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
}
