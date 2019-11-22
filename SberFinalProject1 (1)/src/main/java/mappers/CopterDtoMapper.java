package mappers;

import dto.CopterDto;
import models.Copter;

public class CopterDtoMapper {

    public static CopterDto copterToDto(Copter copter) {
        CopterDto copterDto = new CopterDto();
        copterDto.setId(copter.getId().toString());
        copterDto.setTelemetry(copter.getTelemetry());
        return copterDto;
    }

    public static Copter dtoToCopter(CopterDto copterDto) {
        Copter copter = new Copter();
        copter.setId(Integer.parseInt(copterDto.getId()));
        copter.setTelemetry(copterDto.getTelemetry());
        return copter;
    }
}
