package kafka;

import lombok.extern.java.Log;
import models.Copter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import services.TelemetryService;
import utils.JsonHelper;

import java.io.IOException;
import java.util.logging.Logger;

@Log
@Service
public class Consumer {

    @Autowired
    private TelemetryService telemetryService;

    @KafkaListener(topics = "droneTelemetry", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message" + message);
        Copter copter = JsonHelper.jsonToCopter(message);
        if (copter == null) {
            log.info("incorrect message");
            return;
        }
        telemetryService.save(copter);
    }
}
