package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.ScheduleRepository;
import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.exception.ScheduleNotFoundException;
import com.example.LearningManageSystem.mapper.ScheduleMapper;
import com.example.LearningManageSystem.model.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    public ScheduleEntity addSchedule(ScheduleDTO scheduleDTO) {
        logger.info("Adding student: {}", scheduleDTO);
        ScheduleEntity schedule = scheduleMapper.map(scheduleDTO);
        ScheduleEntity savedSchedule = scheduleRepository.save(schedule);
        logger.info("Schedule added successfully: {}", savedSchedule);
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            logger.warn("Attempted to delete non-existent schedule with id: {}", id);
            throw new ScheduleNotFoundException("Schedule with id " + id + " not found.");
        }
        logger.info("Deleting schedule with id: {}", id);
        scheduleRepository.deleteById(id);
        logger.info("Schedule with id {} deleted successfully.", id);
    }

    public ScheduleEntity updateSchedule(Long id, ScheduleDTO scheduleDTO) {
        logger.info("Updating schedule with id: {}", id);
        if (!scheduleRepository.existsById(id)) {
            logger.warn("schedule with id {} not found for update.", id);
            throw new ScheduleNotFoundException("Schedule with id " + id + " not found.");
        }
        ScheduleEntity schedule = scheduleMapper.map(scheduleDTO);
        schedule.setId(id);
        ScheduleEntity updatedSchedule = scheduleRepository.save(schedule);
        logger.info("Schedule with id {} updated successfully: {}", id, updatedSchedule);
        return updatedSchedule;
    }

    public List<ScheduleEntity> getSchedulesByGroup(Long groupId) {
        logger.info("Requesting schedules for group with ID: {}", groupId);
        List<ScheduleEntity> schedules = scheduleRepository.findByGroupId(groupId);
        logger.info("Number of schedules found: {}", schedules.size());
        return schedules;
    }

    public ScheduleDTO getScheduleById(Long id) {
        logger.info("Requesting schedule with ID: {}", id);
        ScheduleEntity schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Schedule with ID {} not found.", id);
                    return new ScheduleNotFoundException("Schedule with id " + id + " not found.");
                });
        logger.info("Schedule with ID {} found.", id);
        return scheduleMapper.map(schedule);
    }

    public List<ScheduleDTO> getAllSchedules() {
        logger.info("Requesting all schedules");
        List<ScheduleDTO> schedules = scheduleRepository.findAll().stream()
                .map(scheduleMapper::map)
                .collect(Collectors.toList());
        logger.info("Number of all schedules found: {}", schedules.size());
        return schedules;
    }
}
