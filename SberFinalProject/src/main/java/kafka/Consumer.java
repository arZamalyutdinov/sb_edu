package services;

import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Log
@Service
public class Consumer {


    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message" + message);
    }
}
