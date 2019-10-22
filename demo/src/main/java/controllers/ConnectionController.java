package controllers;


import dto.DeviceDto;
import lombok.extern.java.Log;
import models.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.DatabaseConnectionService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConnectionController {

    @Autowired
    private DatabaseConnectionService db;

    @GetMapping(
            path = "devices/get",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Device> get(@RequestParam Integer id) {
        DeviceDto device = null;
        try {
            device = db.getDeviceById(id);
        } catch (Throwable e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new Device(device), HttpStatus.OK);
    }

    @GetMapping(
            path = "devices/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<Integer, Device>>  get() {
        Map<Integer, DeviceDto> deviceDtoMap = null;
        try {
           deviceDtoMap = db.getAllDevices();
        } catch (Throwable e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Map<Integer, Device> mp = new HashMap<>();
        for (Map.Entry<Integer, DeviceDto> entry : deviceDtoMap.entrySet()) {
            mp.put(entry.getKey(), new Device(entry.getValue()));
        }
        return new ResponseEntity<>(mp, HttpStatus.OK);
    }

    @DeleteMapping (
            path = "devices/delete",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> delete(@RequestParam Integer id) {
        try {
            db.deleteDevice(db.getDeviceById(id));
        } catch (Throwable e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

}
