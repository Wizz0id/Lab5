package com.example.Lab2.controller;

import com.example.Lab2.dto.TaskDTO;
import com.example.Lab2.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/projects/api/v1/{projectId}/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable long projectId) {
        return ResponseEntity.ok(taskService.getAllTasksByProjectId(projectId));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable long projectId, @PathVariable int taskId) {
        TaskDTO taskDTO = taskService.getTaskByProjectIdAndTaskId(projectId, taskId);
        if (taskDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(taskDTO);
    }

    @PostMapping()
    public ResponseEntity<TaskDTO> createTask(@PathVariable long projectId, @RequestBody TaskDTO taskDTO) {
        taskDTO.setProjectId(projectId);
        return ResponseEntity.ok(taskService.createTask(taskDTO));
    }
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable long projectId, @PathVariable int taskId, @RequestBody TaskDTO taskDTO) {
        taskDTO.setProjectId(projectId);
        taskDTO.setId(taskId);
        if(taskService.updateTask(projectId,taskId,taskDTO) == 0) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(taskDTO);
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable long projectId, @PathVariable int taskId) {
        taskService.deleteTaskByProjectIDAndTaskID(projectId, taskId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<TaskDTO> deleteCompletedTask(@PathVariable Long projectId) {
        taskService.deleteCompletedTaskByProjectID(projectId);
        return ResponseEntity.ok().build();
    }
}
