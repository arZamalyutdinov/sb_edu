package dao;

import dto.ComponentDto;
import dto.DeviceDto;
import models.DeviceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Repository
public class DeviceDaoImpl extends JdbcDaoSupport implements DeviceDao {

    @Autowired
    DataSource dataSource;

    @Autowired
    ComponentDaoImpl componentDao;

    @PostConstruct
    void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void save(DeviceDto device) {
        String sql = "INSERT INTO devices (id, name, status, compnum, curfail) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            getJdbcTemplate().update(sql,
                    device.getId(),
                    device.getName(),
                    device.getStatus().name(),
                    device.getCompNum(),
                    device.getCurFail()
                    );
        } catch (NullPointerException ex) {
            logger.error(ex);
        }
        for (Map.Entry<Integer, ComponentDto> componentEntry : device.getComponents().entrySet()) {
            ComponentDto component = componentEntry.getValue();
            componentDao.save(component);
        }
    }

    public void update(DeviceDto device) {
        String sql = "UPDATE devices " +
                "SET status = ?, compnum = ?, curfail = ? " +
                "WHERE id = ?";
        try {
            getJdbcTemplate().update(sql,
                    device.getId(),
                    device.getName(),
                    device.getStatus().name(),
                    device.getCompNum(),
                    device.getCurFail()
            );
        } catch (NullPointerException ex) {
            logger.error(ex);
        }
        for (Map.Entry<Integer, ComponentDto> componentEntry : device.getComponents().entrySet()) {
            ComponentDto component = componentEntry.getValue();
            componentDao.update(component);
        }
    }

    public void delete(DeviceDto device) {

        String sql = "DELETE FROM devices WHERE id = ?";
        try {
            getJdbcTemplate().update(sql,
                    device.getId()
            );
        } catch (NullPointerException ex) {
            logger.error(ex);
        }
        for (Map.Entry<Integer, ComponentDto> componentEntry : device.getComponents().entrySet()) {
            ComponentDto component = componentEntry.getValue();
            componentDao.delete(component);
        }
    }

    public DeviceDto findDevice(Integer id) {
        String sql = "SELECT * FROM devices WHERE id = ?";
        return ((DeviceDto) getJdbcTemplate().
                queryForObject(sql,
                        new Object[]{id},
                        (rs, rwNumber) -> {
                            DeviceDto device = new DeviceDto();
                            device.setId(rs.getInt("id"));
                            device.setName(rs.getString("name"));
                            device.setStatus(DeviceStatus.valueOf(rs.getString("status")));
                            device.setCompNum(rs.getInt("compnum"));
                            device.setCurFail(rs.getInt("curfail"));
                            device.setComponents(componentDao.findAllComponentsByDeviceId(device.getId()));
                            return device;
                        }));
    }

    public Map<Integer, DeviceDto> findAllDevices() {
        String sql = "SELECT * FROM devices";
        List<Map<String , Object>> rows = null;
        try {
            rows = getJdbcTemplate().queryForList(sql);
        } catch (NullPointerException ex) {
            logger.error(ex.toString());
        }
        List<DeviceDto> deviceList = new ArrayList<>();
        for (Map<String, Object> row: rows) {
            DeviceDto device = new DeviceDto();
            device.setId((Integer)row.get("id"));
            device.setName((String)row.get("name"));
            device.setCurFail((Integer)row.get("curfail"));
            device.setCompNum((Integer)row.get("compnum"));
            device.setStatus(DeviceStatus.valueOf((String)row.get("status")));
            device.setComponents(componentDao.findAllComponentsByDeviceId(device.getId()));
            deviceList.add(device);
        }
        return deviceList.stream().collect(Collectors.toMap(DeviceDto::getId, x -> x));
    }
}
