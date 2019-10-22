package events;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EventFactory {

    public Event getEvent(Integer type, Integer deviceId, Integer componentID) throws IllegalArgumentException {
        switch (type) {
            case 0:
                return new ErrorEvent(deviceId, componentID);
            case 1:
                return new RestoreEvent(deviceId, componentID);

            default:
                throw new IllegalArgumentException("Unexpected value: " + type);
        }
    }
}
