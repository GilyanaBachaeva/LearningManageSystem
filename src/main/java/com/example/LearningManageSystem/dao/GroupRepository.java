package com.example.LearningManageSystem.dao;

import com.example.LearningManageSystem.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
