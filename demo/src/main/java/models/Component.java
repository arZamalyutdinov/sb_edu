package models;

import dto.ComponentDto;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Component implements Serializable {
    private Integer id;
    private String name;
    private Boolean status;

    public Component(ComponentDto componentDto) {
        this.id = componentDto.getId();
        this.name = componentDto.getName();
        this.status = componentDto.getStatus();
    }
}
