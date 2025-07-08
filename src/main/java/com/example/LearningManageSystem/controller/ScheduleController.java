package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.model.ScheduleEntity;
import com.example.LearningManageSystem.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@Slf4j
public class ScheduleController {

    final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleEntity> addSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        log.info("Request to add schedule: {}", scheduleDTO);
        ScheduleEntity createdSchedule = scheduleService.addSchedule(scheduleDTO);
        log.info("Schedule added successfully: {}", createdSchedule);
        return ResponseEntity.ok(createdSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        log.info("Request to delete schedule with id: {}", id);
        scheduleService.deleteSchedule(id);
        log.info("Schedule with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleEntity> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        log.info("Request to update schedule with id: {}", id);
        ScheduleEntity updatedSchedule = scheduleService.updateSchedule(id, scheduleDTO);
        log.info("Schedule with id {} updated successfully: {}", id, updatedSchedule);
        return ResponseEntity.ok(updatedSchedule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Long id) {
        log.info("Request to fetch schedule with id: {}", id);
        ScheduleDTO schedule = scheduleService.getScheduleById(id);
        log.info("Schedule found: {}", schedule);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        log.info("Request to get all schedules");
        List<ScheduleDTO> schedules = scheduleService.getAllSchedules();
        log.info("Number of schedules found: {}", schedules.size());
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<ScheduleEntity>> getSchedulesByGroup(@PathVariable Long groupId) {
        log.info("Request to get schedule");
        List<ScheduleEntity> schedules = scheduleService.getSchedulesByGroup(groupId);
        log.info("Schedule for this group found: {}", schedules);
        return ResponseEntity.ok(schedules);
    }
}
