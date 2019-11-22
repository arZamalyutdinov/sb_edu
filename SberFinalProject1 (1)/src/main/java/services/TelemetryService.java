package services;

import mappers.CopterDtoMapper;
import models.Copter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CopterDao;

import java.util.Set;


@Service
public class TelemetryService {

    @Autowired
    private CopterDao copterDao;

    public Copter getCopterById(String id) {
        return CopterDtoMapper.dtoToCopter(copterDao.getCopterById(id));
    }

    public void save(Copter copter) {
        copterDao.save(CopterDtoMapper.copterToDto(copter));
    }
    public Set<String> getAllKeys() {
        return copterDao.getAllKeys();
    }
}
