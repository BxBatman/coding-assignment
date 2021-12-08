package com.example.codingassignment;

import com.example.codingassignment.model.EventLog;
import com.example.codingassignment.repository.EventLogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest(args={"logfile.txt"})
class CodingAssignmentApplicationTests {
	@Autowired
	private EventLogRepository eventLogRepository;

	@Test
	public void testCountEvents() {
		List<EventLog> eventLogs = eventLogRepository.findAll();
		Assertions.assertThat(eventLogs.size()).isEqualTo(3);
	}

	@Test
	public void testAlerts() {
		List<EventLog> eventLogs = eventLogRepository.findAll();
		long alertsTrue = eventLogs.stream().filter(EventLog::isAlert).count();
		Assertions.assertThat(alertsTrue).isEqualTo(2);
	}

}
