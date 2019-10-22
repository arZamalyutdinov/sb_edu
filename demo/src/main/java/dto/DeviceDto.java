package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Component;
import models.Device;
import models.DeviceStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto implements Serializable {
    private Integer id;
    private String name;
    private DeviceStatus status;
    private Map<Integer, ComponentDto> components;
    private Integer curFail;
    private Integer compNum;

    public DeviceDto(Device dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.status = dto.getStatus();
        this.curFail = dto.getCurFail();
        this.compNum = dto.getCompNum();
        HashMap<Integer, ComponentDto> mp = new HashMap<>();
        for (Map.Entry<Integer, Component> entry : dto.getComponents().entrySet()) {
            ComponentDto component = new ComponentDto(entry.getValue());
            mp.put(component.getId(), component);
        }
        this.components = mp;
    }
}
