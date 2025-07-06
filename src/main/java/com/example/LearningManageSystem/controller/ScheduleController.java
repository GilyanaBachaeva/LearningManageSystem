package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.model.ScheduleEntity;
import com.example.LearningManageSystem.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    final ScheduleService scheduleService;
    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @PostMapping
    public ResponseEntity<ScheduleEntity> addSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        logger.info("Request to add schedule: {}", scheduleDTO);
        ScheduleEntity createdSchedule = scheduleService.addSchedule(scheduleDTO);
        logger.info("Schedule added successfully: {}", createdSchedule);
        return ResponseEntity.ok(createdSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        logger.info("Request to delete schedule with id: {}", id);
        scheduleService.deleteSchedule(id);
        logger.info("Schedule with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleEntity> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        logger.info("Request to update schedule with id: {}", id);
        ScheduleEntity updatedSchedule = scheduleService.updateSchedule(id, scheduleDTO);
        logger.info("Schedule with id {} updated successfully: {}", id, updatedSchedule);
        return ResponseEntity.ok(updatedSchedule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Long id) {
        logger.info("Request to fetch schedule with id: {}", id);
        ScheduleDTO schedule = scheduleService.getScheduleById(id);
        logger.info("Schedule found: {}", schedule);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        logger.info("Request to get all schedules");
        List<ScheduleDTO> schedules = scheduleService.getAllSchedules();
        logger.info("Number of schedules found: {}", schedules.size());
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<ScheduleEntity>> getSchedulesByGroup(@PathVariable Long groupId) {
        logger.info("Request to get schedule");
        List<ScheduleEntity> schedules = scheduleService.getSchedulesByGroup(groupId);
        logger.info("Schedule for this group found: {}", schedules);
        return ResponseEntity.ok(schedules);
    }
}
