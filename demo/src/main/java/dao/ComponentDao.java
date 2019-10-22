package dao;



import dto.ComponentDto;

import java.util.Map;

public interface ComponentDao {
    void save(ComponentDto component);
    void update(ComponentDto component);
    ComponentDto findComponent(Integer id);
    Map<Integer, ComponentDto> findAllComponentsByDeviceId(Integer deviceId);
    void delete(ComponentDto component);
}
