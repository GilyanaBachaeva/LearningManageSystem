package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.LearningManageSystemApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(classes = LearningManageSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
public class AbstractIT {

    @SuppressWarnings("rawtypes")
    protected static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
                .withDatabaseName("lms_db")
                .withUsername("postgres")
                .withPassword("postgres");

    static {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("DB_URL", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("DB_USERNAME", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("DB_PASSWORD", postgreSQLContainer::getPassword);
    }
}
