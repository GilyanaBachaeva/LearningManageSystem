package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.ScheduleRepository;
import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.exception.ScheduleNotFoundException;
import com.example.LearningManageSystem.mapper.ScheduleMapper;
import com.example.LearningManageSystem.model.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleEntity addSchedule(ScheduleDTO scheduleDTO) {
        log.info("Adding student: {}", scheduleDTO);
        ScheduleEntity schedule = scheduleMapper.map(scheduleDTO);
        ScheduleEntity savedSchedule = scheduleRepository.save(schedule);
        log.info("Schedule added successfully: {}", savedSchedule);
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent schedule with id: {}", id);
            throw new ScheduleNotFoundException("Schedule with id " + id + " not found.");
        }
        log.info("Deleting schedule with id: {}", id);
        scheduleRepository.deleteById(id);
        log.info("Schedule with id {} deleted successfully.", id);
    }

    public ScheduleEntity updateSchedule(Long id, ScheduleDTO scheduleDTO) {
        log.info("Updating schedule with id: {}", id);
        if (!scheduleRepository.existsById(id)) {
            log.warn("schedule with id {} not found for update.", id);
            throw new ScheduleNotFoundException("Schedule with id " + id + " not found.");
        }
        ScheduleEntity schedule = scheduleMapper.map(scheduleDTO);
        schedule.setId(id);
        ScheduleEntity updatedSchedule = scheduleRepository.save(schedule);
        log.info("Schedule with id {} updated successfully: {}", id, updatedSchedule);
        return updatedSchedule;
    }

    public List<ScheduleEntity> getSchedulesByGroup(Long groupId) {
        log.info("Requesting schedules for group with ID: {}", groupId);
        List<ScheduleEntity> schedules = scheduleRepository.findByGroupId(groupId);
        log.info("Number of schedules found: {}", schedules.size());
        return schedules;
    }

    public ScheduleDTO getScheduleById(Long id) {
        log.info("Requesting schedule with ID: {}", id);
        ScheduleEntity schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Schedule with ID {} not found.", id);
                    return new ScheduleNotFoundException("Schedule with id " + id + " not found.");
                });
        log.info("Schedule with ID {} found.", id);
        return scheduleMapper.map(schedule);
    }

    public List<ScheduleDTO> getAllSchedules() {
        log.info("Requesting all schedules");
        List<ScheduleDTO> schedules = scheduleRepository.findAll().stream()
                .map(scheduleMapper::map)
                .collect(Collectors.toList());
        log.info("Number of all schedules found: {}", schedules.size());
        return schedules;
    }
}
