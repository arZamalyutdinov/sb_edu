package SberFinalProject;

import Controllers.TelemetryController;
import models.Copter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import redis.RedisConfiguration;
import repositories.CopterDao;
import kafka.Consumer;
import kafka.Producer;
import services.TelemetryService;

@ComponentScan(basePackageClasses = {
        CopterDao.class,
        RedisConfiguration.class,
        Copter.class,
        TelemetryController.class,
        TelemetryService.class,
		Producer.class,
		Consumer.class
})
@SpringBootApplication
public class SberFinalProjectApplication {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SberFinalProjectApplication.class, args);
	}
}
