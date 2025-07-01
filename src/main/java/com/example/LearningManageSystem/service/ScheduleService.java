package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.ScheduleRepository;
import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.exception.ScheduleNotFoundException;
import com.example.LearningManageSystem.mapper.ScheduleMapper;
import com.example.LearningManageSystem.model.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleEntity addSchedule(ScheduleDTO scheduleDTO) {
        ScheduleEntity schedule = scheduleMapper.map(scheduleDTO);
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ScheduleNotFoundException("Schedule with id " + id + " not found.");
        }
        scheduleRepository.deleteById(id);
    }

    public ScheduleEntity updateSchedule(Long id, ScheduleDTO scheduleDTO) {
        if (!scheduleRepository.existsById(id)) {
            throw new ScheduleNotFoundException("Schedule with id " + id + " not found.");
        }
        ScheduleEntity schedule = scheduleMapper.map(scheduleDTO);
        schedule.setId(id);
        return scheduleRepository.save(schedule);
    }

    public List<ScheduleEntity> getSchedulesByGroup(Long groupId) {
        return scheduleRepository.findByGroupId(groupId);
    }

    public ScheduleDTO getScheduleById(Long id) {
        ScheduleEntity schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule with id " + id + " not found."));
        return scheduleMapper.map(schedule);
    }

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(scheduleMapper::map)
                .collect(Collectors.toList());
    }
}
