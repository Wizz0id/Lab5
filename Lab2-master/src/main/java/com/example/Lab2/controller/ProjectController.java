package com.example.Lab2.controller;

import com.example.Lab2.dto.ProjectDTO;
import com.example.Lab2.dto.UncomplitedDTO;
import com.example.Lab2.service.ProjectService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/projects/api/v1")
@Slf4j
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @GetMapping
    public List<ProjectDTO> findBySearch(@RequestParam @Nullable String search) {
        if(search == null) return projectService.getAllProjects();
        return projectService.getProjectsBySearch(search);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable long projectId) {
        ProjectDTO projectDTO = projectService.getProjectsById(projectId);
        if(projectDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(projectDTO);
    }

    @PostMapping("/new")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO projectDTOCreated = projectService.createProject(projectDTO);
        return ResponseEntity.ok(projectDTOCreated);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable long projectId, @RequestBody ProjectDTO projectDTO) {
        projectDTO.setId(projectId);
        logger.debug("Updating project with ID: " + projectDTO.getId());
        logger.debug("New name: " + projectDTO.getName());
        logger.debug("New description: " + projectDTO.getDescription());
        logger.debug("New start date: " + projectDTO.getStartDate());
        logger.debug("New end date: " + projectDTO.getEndDate());
        int t = projectService.updateProject(projectDTO);
        logger.debug("Update result: " + t);
        if( t == 0) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(projectDTO);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> deleteProject(@PathVariable long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/uncompleted")
    public ResponseEntity<List<UncomplitedDTO>> getUncompleted() {
        List<UncomplitedDTO> a = projectService.findUncompletedTasks();
        return ResponseEntity.ok(a);
    }
}
