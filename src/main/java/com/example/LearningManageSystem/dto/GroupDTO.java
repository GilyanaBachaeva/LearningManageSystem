package com.example.LearningManageSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private Long id;

    @NotBlank(message = "Name cannot be null or empty")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    private Set<Long> studentIds;
}

