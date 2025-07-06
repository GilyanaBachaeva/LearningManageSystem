package com.example.LearningManageSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;

    @NotBlank(message = "Name cannot be null or empty")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String firstName;

    @NotBlank(message = "Surname cannot be null or empty")
    @Size(min = 3, message = "Surname must be at least 3 characters long")
    private String lastName;

    private Long groupId;
}
