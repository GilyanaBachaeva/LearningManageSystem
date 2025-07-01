package com.example.LearningManageSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private Long id;
    private Long groupId;
    private Long teacherId;
    private Long courseId;
    private LocalDateTime date;
}

