package com.example.LearningManageSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private Long id;

    @NotNull(message = "Group ID cannot be null")
    private Long groupId;

    @NotNull(message = "Teacher ID cannot be null")
    private Long teacherId;

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

    private LocalDateTime date;
}

