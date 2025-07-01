package com.example.LearningManageSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private Long teacherId;
    private Set<Long> scheduleIds;
}

