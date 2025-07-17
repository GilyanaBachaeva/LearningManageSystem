package com.example.LearningManageSystem.mapper;

import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.model.ScheduleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    ScheduleDTO map(ScheduleEntity schedule);
    ScheduleEntity map(ScheduleDTO scheduleDTO);
}
