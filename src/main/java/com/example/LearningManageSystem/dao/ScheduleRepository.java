package com.example.LearningManageSystem.dao;

import com.example.LearningManageSystem.model.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findByGroupId(Long groupId);
}
