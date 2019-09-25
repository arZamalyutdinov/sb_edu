abstract class Event {
    private Integer deviceID;
    private Integer componentId;

    public Event(Integer deviceID, Integer componentId) {
        this.deviceID = deviceID;
        this.componentId = componentId;
    }

    public Integer getComponentId() {
        return componentId;
    }

    public Integer getDeviceID() {
        return deviceID;
    }
}
