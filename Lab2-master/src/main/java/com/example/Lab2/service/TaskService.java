package com.example.Lab2.service;

import com.example.Lab2.converter.Mapper;
import com.example.Lab2.dto.TaskDTO;
import com.example.Lab2.entity.Project;
import com.example.Lab2.entity.Task;
import com.example.Lab2.repository.ProjectRepository;
import com.example.Lab2.repository.TaskRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Data
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    private final Mapper mapper;

    public List<TaskDTO> getAllTasksByProjectId(long projectId) {
        return taskRepository.findAllByProjectId(projectId).stream().map(mapper::convertTaskToDTO).toList();
    }

    public TaskDTO getTaskByProjectIdAndTaskId(long projectId, long taskId) {
        return mapper.convertTaskToDTO(taskRepository.findTaskByProjectIdAndId(projectId, taskId));
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task newTask = mapper.convertToTask(taskDTO);
        return mapper.convertTaskToDTO(taskRepository.save(newTask));
    }

    public int updateTask(long projectId, long taskId, TaskDTO taskDTO) {
        return taskRepository.updateTask(projectId, taskId,
                taskDTO.getName(),
                taskDTO.getDescription(),
                taskDTO.getEndDate(),
                taskDTO.isCompleted());
    }

    public void deleteTaskByProjectIDAndTaskID(long projectId, long taskId) {
        taskRepository.deleteByProjectIdAndId(projectId, taskId);
    }

    public void deleteCompletedTaskByProjectID(long projectId) {
        taskRepository.deleteCompletedTaskByProjectId(projectId);
    }
}
