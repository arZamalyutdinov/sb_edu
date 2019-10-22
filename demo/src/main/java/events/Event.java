package events;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.Device;

import java.util.HashMap;
import java.util.HashSet;

@Data
@AllArgsConstructor
public abstract class Event {
    private Integer deviceID;
    private Integer componentId;

}
