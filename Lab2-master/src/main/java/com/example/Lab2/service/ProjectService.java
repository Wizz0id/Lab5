package com.example.Lab2.service;

import com.example.Lab2.converter.Mapper;
import com.example.Lab2.dto.ProjectDTO;
import com.example.Lab2.dto.UncomplitedDTO;
import com.example.Lab2.entity.Project;
import com.example.Lab2.repository.ProjectRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Data
@Service
public class ProjectService {
    @Autowired
    private final Mapper mapper;
    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream().map(mapper::convertToDTO).toList();
    }

    public List<ProjectDTO> getProjectsBySearch(String search) {
        return projectRepository.findBySearch(search).stream().map(mapper::convertToDTO).toList();
    }

    public ProjectDTO getProjectsById(long id) {
        return (ProjectDTO) projectRepository.findAllById(Collections.singleton(id)).stream().map(mapper::convertToDTO).toList();
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = mapper.convertToProject(projectDTO);
        return mapper.convertToDTO(projectRepository.save(project));
    }

    public int updateProject(ProjectDTO projectDTO) {
        return projectRepository.updateProject(projectDTO.getId(),
                projectDTO.getName(),
                projectDTO.getDescription(),
                projectDTO.getStartDate(),
                projectDTO.getEndDate());
    }

    public void deleteProject(long id) {
        projectRepository.deleteById(id);
    }

    public List<UncomplitedDTO> findUncompletedTasks() {
        return projectRepository.findUncompletedTasks();
    }
}
