package Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.java.Log;
import models.Copter;
import models.Location;
import models.Telemetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kafka.Consumer;
import kafka.Producer;
import services.TelemetryService;
import utils.JsonHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Log
@RestController
public class TelemetryController {

    @Autowired
    private TelemetryService telemetryService;

    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

    @GetMapping(path = "/telemetry/copter")
    public ResponseEntity<Copter> getCopter(@RequestParam String id) {
        log.info("consumed id: " + id);
        try {
            return new ResponseEntity<>(telemetryService.getCopterById(id), HttpStatus.OK);
        } catch (Throwable ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/allKeys")
    public ResponseEntity<Set<String>> getAllKeys() {
        return new ResponseEntity<>(telemetryService.getAllKeys(), HttpStatus.OK);
    }


    @GetMapping(path = "copters")
    public ResponseEntity<List<Copter>> getCopters() {

        List<Copter> lst = new ArrayList<>();
        Set<String> keys = telemetryService.getAllKeys();
//        log.info("here #1");
        for (String e: keys) {
            lst.add(telemetryService.getCopterById(e));


        }
//        log.info("here #2");
        ResponseEntity<List<Copter>> msg = new ResponseEntity<List<Copter>>(lst, HttpStatus.OK);

        return msg;
    }
}
