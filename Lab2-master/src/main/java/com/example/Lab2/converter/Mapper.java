package com.example.Lab2.converter;

import com.example.Lab2.controller.ProjectController;
import com.example.Lab2.dto.ProjectDTO;
import com.example.Lab2.dto.TaskDTO;
import com.example.Lab2.entity.Project;
import com.example.Lab2.entity.Task;
import com.example.Lab2.repository.ProjectRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Mapper {
    private final ProjectRepository projectRepository;

    public ProjectDTO convertToDTO(Project project) {
        if (project == null) {
            return null;
        }
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setEndDate(project.getEndDate());
        return projectDTO;
    }

    public Project convertToProject(ProjectDTO projectDTO) {
        if (projectDTO == null) {
            return null;
        }
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        return project;
    }

    public TaskDTO convertTaskToDTO(Task task) {
        return new TaskDTO(task.getId(),
                task.getName(),
                task.getDescription(),
                task.getEndDate(),
                task.isCompleted(),
                task.getProject().getId());
    }

    public Task convertToTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setEndDate(taskDTO.getEndDate());
        task.setCompleted(taskDTO.isCompleted());
        task.setProject(projectRepository.findById(taskDTO.getProjectId()).orElse(null));
        return task;
    }
}
