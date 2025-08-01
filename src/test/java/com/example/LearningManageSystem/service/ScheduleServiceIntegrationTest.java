package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.CourseRepository;
import com.example.LearningManageSystem.dao.GroupRepository;
import com.example.LearningManageSystem.dao.StudentRepository;
import com.example.LearningManageSystem.dao.TeacherRepository;
import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.model.CourseEntity;
import com.example.LearningManageSystem.model.GroupEntity;
import com.example.LearningManageSystem.model.ScheduleEntity;
import com.example.LearningManageSystem.model.StudentEntity;
import com.example.LearningManageSystem.model.TeacherEntity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleServiceIntegrationTest extends AbstractIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private GroupRepository groupRepository;

    private List<ScheduleDTO> scheduleDTOs;
    private List<Long> scheduleIds;

    @BeforeEach
    public void setUp() {

        scheduleIds = new ArrayList<>();
        scheduleDTOs = new ArrayList<>();

        Long groupId = createTestGroup("Hobbiton Group");
        Long teacherId1 = createTestTeacher("Gandalf", "the Grey");
        Long teacherId2 = createTestTeacher("Aragorn", "Son of Arathorn");
        Long courseId1 = createTestCourse("How to Get to Mordor and Back");
        Long courseId2 = createTestCourse("Advanced Survival Skills");

        createTestStudent("Frodo", "Baggins", groupId);
        createTestStudent("Samwise", "Gamgee", groupId);

        addSchedules(groupId, teacherId1, courseId1, 0);
        addSchedules(groupId, teacherId2, courseId2, 3);
    }

    @AfterEach
    public void tearDown() {
        courseRepository.deleteAll();
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        groupRepository.deleteAll();
        scheduleIds.clear();
        scheduleDTOs.clear();
    }

    @Test
    void testAddSchedules() {
        for (ScheduleDTO scheduleDTO : scheduleDTOs) {
            ResponseEntity<ScheduleEntity> response = restTemplate.postForEntity("/schedules",
                    scheduleDTO, ScheduleEntity.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            scheduleIds.add(response.getBody().getId());
        }
    }

    @Test
    void testGetSchedules() {
        testAddSchedules();
        ResponseEntity<List<ScheduleDTO>> response = restTemplate.exchange(
                "/schedules",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ScheduleDTO>>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(scheduleDTOs.size());
    }

    @Test
    public void testDeleteSchedule() {
        testAddSchedules();

        Long scheduleId = scheduleIds.get(0);

        ResponseEntity<Void> response = restTemplate.exchange(
                "/schedules/" + scheduleId,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<List<ScheduleDTO>> getResponse = restTemplate.exchange(
                "/schedules",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ScheduleDTO>>() {}
        );
        assertThat(getResponse.getBody()).hasSize(scheduleDTOs.size() - 1);
    }

    @Test
    public void testAddScheduleWithInvalidData() {
        ScheduleDTO invalidScheduleDTO = new ScheduleDTO();
        invalidScheduleDTO.setGroupId(null);
        invalidScheduleDTO.setTeacherId(null);
        invalidScheduleDTO.setCourseId(null);
        invalidScheduleDTO.setDate(LocalDateTime.now());

        ResponseEntity<ScheduleEntity> response = restTemplate.postForEntity("/schedules",
                invalidScheduleDTO, ScheduleEntity.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetNonExistentSchedule() {
        Long nonExistentScheduleId = 999L;

        ResponseEntity<String> response = restTemplate.getForEntity("/schedules/" +
                nonExistentScheduleId, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private void addSchedules(Long groupId, Long teacherId, Long courseId, int startDay) {
        for (int i = 0; i < 3; i++) {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO.setGroupId(groupId);
            scheduleDTO.setTeacherId(teacherId);
            scheduleDTO.setCourseId(courseId);
            scheduleDTO.setDate(LocalDateTime.now().plusDays(i + startDay));
            scheduleDTOs.add(scheduleDTO);
        }
    }

    private Long createTestGroup(String name) {
        GroupEntity group = new GroupEntity();
        group.setName(name);
        groupRepository.save(group);
        return group.getId();
    }

    private Long createTestTeacher(String firstName, String lastName) {
        TeacherEntity teacher = new TeacherEntity();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacherRepository.save(teacher);
        return teacher.getId();
    }

    private Long createTestCourse(String name) {
        CourseEntity course = new CourseEntity();
        course.setName(name);
        courseRepository.save(course);
        return course.getId();
    }

    private void createTestStudent(String firstName, String lastName, Long groupId) {
        StudentEntity student = new StudentEntity();
        student.setFirstName(firstName);
        student.setLastName(lastName);

        GroupEntity group = groupRepository.findById(groupId).orElseThrow();
        student.setGroup(group);

        studentRepository.save(student);
    }
}
