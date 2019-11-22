package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Copter;

import java.io.Reader;
import java.io.StringReader;

public class JsonHelper {

    static public String copterToJson(Copter copter) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(copter);
    }

    public static Copter jsonToCopter(String jsonToParse) throws JsonProcessingException {
        System.out.println(jsonToParse);
        ObjectMapper objectMapper = new ObjectMapper();
        Reader reader = new StringReader(jsonToParse);
        try {
             return objectMapper.readValue(reader, Copter.class);
        }catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
