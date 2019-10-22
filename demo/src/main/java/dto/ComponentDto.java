package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentDto implements Serializable {
    private Integer id;
    private String name;
    private Boolean status;

    public ComponentDto(Component componentDto) {
        this.id = componentDto.getId();
        this.name = componentDto.getName();
        this.status = componentDto.getStatus();
    }
}
