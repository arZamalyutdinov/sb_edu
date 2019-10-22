package main;

import controllers.ConnectionController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import services.ProcessService;

@SpringBootApplication
@ComponentScan(basePackageClasses = ConnectionController.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        ProcessService prc = new ProcessService();
        prc.process();
    }

}
