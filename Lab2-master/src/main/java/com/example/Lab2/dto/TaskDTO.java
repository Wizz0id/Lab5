package com.example.Lab2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private long id;
    private String name;
    private String description;
    private LocalDate endDate;
    private boolean completed;
    private long projectId;
}
