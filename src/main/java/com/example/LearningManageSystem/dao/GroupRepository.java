package com.example.LearningManageSystem.dao;

import com.example.LearningManageSystem.model.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}
