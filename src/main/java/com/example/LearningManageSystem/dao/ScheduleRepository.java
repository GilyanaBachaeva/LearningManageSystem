package com.example.LearningManageSystem.dao;

import com.example.LearningManageSystem.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByGroupId(Long groupId);
}
