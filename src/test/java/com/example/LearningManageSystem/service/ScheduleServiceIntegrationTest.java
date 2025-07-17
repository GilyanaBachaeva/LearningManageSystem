package com.example.LearningManageSystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ScheduleServiceIntegrationTest extends AbstractIT{

	@Autowired
	private ScheduleService scheduleService;

	@Test
	void contextLoads() {

	}

}
