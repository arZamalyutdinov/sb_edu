package SberFinalProject;

import com.fasterxml.jackson.core.JsonProcessingException;
import kafka.Consumer;
import kafka.Producer;
import models.Copter;
import models.Telemetry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import services.TelemetryService;
import utils.JsonHelper;

@SpringBootTest
class SberFinalProjectApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private Consumer consumer;

	@Autowired
	private Producer producer;

	@Autowired
	private TelemetryService telemetryService;

	@Test
	public void saveCopter() {
		Copter copter = new Copter(14, new Telemetry(12.8, 13, null));
//		redisTemplate.opsForValue().set(copter.getId(), copter);
        System.out.println(redisTemplate.opsForValue().get(copter.getId()));

	}

	@Test
	public void tryToSaveCopter() {
		Copter copter = new Copter(27, new Telemetry(15.8, 120, null));
		try {
			producer.sendMessage(JsonHelper.copterToJson(copter));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Test
	void contextLoads() {
	}

}
