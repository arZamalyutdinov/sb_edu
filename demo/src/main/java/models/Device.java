package models;

import dto.ComponentDto;
import dto.DeviceDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Device implements Serializable {
    private Integer id;
    private String name;
    private DeviceStatus status;
    private Map<Integer, Component> components;
    private Integer curFail;
    private Integer compNum;

    public Device(DeviceDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.status = dto.getStatus();
        this.curFail = dto.getCurFail();
        this.compNum = dto.getCompNum();
        HashMap<Integer, Component> mp = new HashMap<>();
        for (Map.Entry<Integer, ComponentDto> entry : dto.getComponents().entrySet()) {
            Component component = new Component(entry.getValue());
            mp.put(component.getId(), component);
        }
        this.components = mp;
    }

    public Device(Integer id, String name, DeviceStatus status, HashMap<Integer, Component> components) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.components = components;
        this.curFail = 0;
        this.compNum = components.size();
    }

    public void changeComponentStatus(Integer compId, boolean status) throws RuntimeException {
        Component comp = components.get(compId);
        boolean curStatus = comp.getStatus();
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
}
