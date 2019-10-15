package models;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Entity
@Table(name = "devices")
public class Device implements Serializable {
    @Id
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    @Enumerated(EnumType.STRING)
    private DeviceStatus status;
    @OneToMany(targetEntity = Component.class, mappedBy = "id.device", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Map<Integer, Component> components;
    private Integer curFail;
    private Integer compNum;

    public Map<Integer, Component> getComponents() {
        return components;
    }
    public Integer getcompNum() {
        return compNum;
    }

    public Device(Integer id, String name, DeviceStatus status, HashMap<Integer, Component> components) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.components = components;
        for (Map.Entry<Integer, Component> entry : components.entrySet()) {
            Component c = entry.getValue();
            c.setDevice(this);
        }
        this.curFail = 0;
        this.compNum = components.size();
        
    }
    public void changeComponentStatus(Integer compId, boolean status) throws RuntimeException {
        Component comp = components.get(compId);
        boolean curStatus  = comp.getStatus();
        if (curStatus != status) {
            if (!curStatus) {
                --curFail;
                if (curFail < components.size() / 2) {
                    this.status = DeviceStatus.Warning;
                }
                if (curFail == 0) {
                    this.status = DeviceStatus.Normal;
                }
            } else {
                ++curFail;
                this.status = DeviceStatus.Warning;
                if (curFail >= components.size() / 2) {
                    this.status = DeviceStatus.Error;
                }
            }
        } else {
            throw new RuntimeException("Same status!");
        }
        comp.setStatus(status);
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", components=" + components +
                ", curFail=" + curFail +
                '}' + '\n';
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
}
