package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.model.ScheduleEntity;
import com.example.LearningManageSystem.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "Schedules")
public class ScheduleController {

    final ScheduleService scheduleService;

    @PostMapping
    @ApiOperation(value = "Add Schedule", notes = "Creates a new schedule")
    public ResponseEntity<ScheduleEntity> addSchedule(@ApiParam(value = "Schedules data to be added", required = true)
                                                      @Valid @RequestBody ScheduleDTO scheduleDTO) {
        log.info("Request to add schedule: {}", scheduleDTO);
        ScheduleEntity createdSchedule = scheduleService.addSchedule(scheduleDTO);
        log.info("Schedule added successfully: {}", createdSchedule);
        return ResponseEntity.ok(createdSchedule);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Schedule", notes = "Deletes a schedule by its ID")
    public ResponseEntity<Void> deleteSchedule(@ApiParam(value = "Schedule ID", required = true)
                                               @PathVariable Long id) {
        log.info("Request to delete schedule with id: {}", id);
        scheduleService.deleteSchedule(id);
        log.info("Schedule with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Schedule", notes = "Updates the information of a schedule by its ID")
    public ResponseEntity<ScheduleEntity> updateSchedule(@ApiParam(value = "Schedule ID", required = true)
                                                         @PathVariable Long id,
                                                         @ApiParam(value = "Updated schedule data", required = true)
                                                         @Valid @RequestBody ScheduleDTO scheduleDTO) {
        log.info("Request to update schedule with id: {}", id);
        ScheduleEntity updatedSchedule = scheduleService.updateSchedule(id, scheduleDTO);
        log.info("Schedule with id {} updated successfully: {}", id, updatedSchedule);
        return ResponseEntity.ok(updatedSchedule);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Schedule by ID", notes = "Returns information about a schedule by its ID")
    public ResponseEntity<ScheduleDTO> getScheduleById(@ApiParam(value = "Schedule ID", required = true)
                                                       @PathVariable Long id) {
        log.info("Request to fetch schedule with id: {}", id);
        ScheduleDTO schedule = scheduleService.getScheduleById(id);
        log.info("Schedule found: {}", schedule);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping
    @ApiOperation(value = "Get All Schedules", notes = "Returns a list of all schedules")
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        log.info("Request to get all schedules");
        List<ScheduleDTO> schedules = scheduleService.getAllSchedules();
        log.info("Number of schedules found: {}", schedules.size());
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/group/{groupId}")
    @ApiOperation(value = "Get Schedule by Group", notes = "Returns information about a schedule by groups ID")
    public ResponseEntity<List<ScheduleEntity>> getSchedulesByGroup(@ApiParam(value = "Group ID", required = true)
                                                                    @PathVariable Long groupId) {
        log.info("Request to get schedule");
        List<ScheduleEntity> schedules = scheduleService.getSchedulesByGroup(groupId);
        log.info("Schedule for this group found: {}", schedules);
        return ResponseEntity.ok(schedules);
    }
}
