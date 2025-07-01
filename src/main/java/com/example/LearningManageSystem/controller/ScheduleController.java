package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.model.ScheduleEntity;
import com.example.LearningManageSystem.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleEntity> addSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleEntity createdSchedule = scheduleService.addSchedule(scheduleDTO);
        return ResponseEntity.ok(createdSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleEntity> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO) {
        ScheduleEntity updatedSchedule = scheduleService.updateSchedule(id, scheduleDTO);
        return ResponseEntity.ok(updatedSchedule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Long id) {
        ScheduleDTO schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<ScheduleDTO> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<ScheduleEntity>> getSchedulesByGroup(@PathVariable Long groupId) {
        List<ScheduleEntity> schedules = scheduleService.getSchedulesByGroup(groupId);
        return ResponseEntity.ok(schedules);
    }
}
