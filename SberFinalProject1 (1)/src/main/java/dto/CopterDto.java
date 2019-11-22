package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Telemetry;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Copter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CopterDto implements Serializable {

    @Id
    private String id;
    private Telemetry telemetry;

}
