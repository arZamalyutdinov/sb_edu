package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {


    @Autowired
    GenerationService generationService;

    public void process() {
//        System.out.println(generationService);
//        System.out.println(generationService.generateDevices(10, 10));

//        ConnectionController controller = new ConnectionController();
//        GenerationService generator = new GenerationService();
//        Map<Integer, Device> devices = generator.generateDevices(10, 30);
//        System.out.println(devices);
//
//        Queue<Event> events = generator.generateEvents(200, devices);
//        EventHandlingService eventHandle = new EventHandlingService(events, devices);
//        eventHandle.handleEvents();
//        System.out.println(devices);
//        HibernateSessionFactoryUtil.closeSessionFactory();
    }


}
