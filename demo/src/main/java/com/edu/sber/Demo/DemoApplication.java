package com.edu.sber.Demo;

import controllers.ConnectionController;
import dao.ComponentDaoImpl;
import dao.DeviceDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import services.*;

@SpringBootApplication
@ComponentScan(basePackageClasses = {
		ConnectionController.class,
		DeviceDaoImpl.class,
		ProcessService.class,
		DatabaseConnectionService.class,
		GenerationService.class,
		ComponentDaoImpl.class,
		DeviceDaoImpl.class,
		Context.class,
		ErrorStrategy.class,
		RestoreStrategy.class,
		EventHandlingService.class
	}
		)
public class DemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		ProcessService processService = context.getBean(ProcessService.class);
		processService.process();
	}

}
