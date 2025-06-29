package com.example.LearningManageSystem.mapper;

import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.model.Schedule;

public interface ScheduleMapper {
    ScheduleDTO map(Schedule schedule);
    Schedule map(ScheduleDTO scheduleDTO);
}
