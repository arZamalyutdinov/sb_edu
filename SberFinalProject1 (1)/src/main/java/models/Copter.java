package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Copter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Copter implements Serializable {

    @Id
    private Integer id;
    private Telemetry telemetry;

}
