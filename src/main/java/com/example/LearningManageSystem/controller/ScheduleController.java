package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.model.Schedule;
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
    public ResponseEntity<Schedule> addSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule createdSchedule = scheduleService.addSchedule(scheduleDTO);
        return ResponseEntity.ok(createdSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDTO scheduleDTO) {
        Schedule updatedSchedule = scheduleService.updateSchedule(id, scheduleDTO);
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
    public ResponseEntity<List<Schedule>> getSchedulesByGroup(@PathVariable Long groupId) {
        List<Schedule> schedules = scheduleService.getSchedulesByGroup(groupId);
        return ResponseEntity.ok(schedules);
    }
}
