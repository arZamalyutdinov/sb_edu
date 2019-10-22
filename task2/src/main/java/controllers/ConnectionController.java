package controllers;


//import netscape.javascript.JSObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConnectionController {

    @GetMapping(
            path = "hello"
    )
    public Integer prt() {
        Integer t = 1;
        System.out.println("Done! \n Wow!");
        return t;
    }

}
