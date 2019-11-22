package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Telemetry implements Serializable {
    private Double speed;
    private Integer battery;
    private Location location;
}
