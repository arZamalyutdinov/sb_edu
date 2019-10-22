package dao;

import dto.ComponentDto;
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

@Repository
@Transactional
public class ComponentDaoImpl extends JdbcDaoSupport implements ComponentDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    void initialize() {
        setDataSource(dataSource);
    }


    @Override
    public void save(ComponentDto component) {
        final String sql = "INSERT INTO COMPONENTS (id, name, status) " +
                "VALUES (?, ?, ?)";
        try {
            getJdbcTemplate().update(sql,
                    component.getId(),
                    component.getName(),
                    component.getStatus());
        } catch (NullPointerException ex) {
            logger.error(ex);
        }
    }

    @Override
    public void update(ComponentDto component) {
        final String sql = "UPDATE COMPONENTS " +
                "SET status = ? "+
                "WHERE name = ?";
        try {
            getJdbcTemplate().update(sql,
                    component.getStatus(),
                    component.getName());
        } catch (NullPointerException ex) {
            logger.error(ex);
        }
    }

    @Override
    public void delete(ComponentDto component) {
        final String sql = "DELETE FROM COMPONENTS " +
                "WHERE name = ?";
        try {
            getJdbcTemplate().update(sql,
                    component.getName());
        } catch (NullPointerException ex) {
            logger.error(ex);
        }
    }

    @Override
    public ComponentDto findComponent(Integer id) {
        String sql = "SELECT * FROM components WHERE id = ? ";
        return ((ComponentDto) getJdbcTemplate().
                queryForObject(sql,
                        new Object[]{id},
                        (rs, rwNumber) -> {
                            ComponentDto component = new ComponentDto();
                            component.setId(rs.getInt("id"));
                            component.setName(rs.getString("name"));
                            component.setStatus(rs.getBoolean("status"));
                            return component;
                        }));
    }

    @Override
    public Map<Integer, ComponentDto> findAllComponentsByDeviceId(Integer DeviceId) {
        String sql = "SELECT * FROM components a " +
                "WHERE a.name IN (SELECT componentname from " +
                "devicecomponentlink where deviceid = ?)";
        List<Map<String , Object>> rows = null;
        try {
            rows = getJdbcTemplate().queryForList(sql,
                    DeviceId);
        } catch (NullPointerException ex) {
            logger.error(ex.toString());
        }
        List<ComponentDto> componentList = new ArrayList<>();
        for (Map<String, Object> row: rows) {
            ComponentDto component = new ComponentDto();
            component.setId((Integer)row.get("id"));
            component.setName((String)row.get("name"));
            component.setStatus((Boolean)row.get("status"));
            componentList.add(component);
        }
        return componentList.stream().collect(Collectors.toMap(ComponentDto::getId, x -> x));
    }
}
