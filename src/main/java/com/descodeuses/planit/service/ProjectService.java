package com.descodeuses.planit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.descodeuses.planit.dto.ProjectDto;
import com.descodeuses.planit.entity.ProjectEntity;
import com.descodeuses.planit.repository.ProjectRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectService {
    private final ProjectRepository repository;

    public ProjectService (ProjectRepository repository) {
        this.repository = repository;
    }

    private ProjectDto convertProjectToDTO(ProjectEntity project) {
        ProjectDto dto = new ProjectDto(
            project.getId(),
            project.getTitle(),
            project.getStartDate(),
            project.getEndDate(),
            project.getStatus());
        return dto;
    }

    private ProjectEntity convertProjectToEntity(ProjectDto dto){
        ProjectEntity project= new ProjectEntity();
        project.setId(dto.getId());
        project.setTitle(dto.getTitle());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        project.setStatus(dto.getStatus());

        return project;
    }

    //Get all
    public List<ProjectDto> getAll() {
        List<ProjectEntity> projectList = repository.findAll();
        List<ProjectDto> dtoList = new ArrayList<>();

        for ( ProjectEntity item: projectList) {
            dtoList.add(convertProjectToDTO(item));
        }
        return dtoList;
    }

    // Get by id
    public ProjectDto getProjectById( Long id) {
        Optional<ProjectEntity> projectById = repository.findById(id);
        if(projectById.isEmpty()) {
            throw new EntityNotFoundException("Project not found with this id : "+ id);
        }
        return convertProjectToDTO(projectById.get());
    }

    //Create project
    public ProjectDto create (ProjectDto dto) {
        ProjectEntity entity = convertProjectToEntity(dto);
        ProjectEntity savedEntity = repository.save(entity);
        return convertProjectToDTO(savedEntity);
    }

    // Update project
    public ProjectDto update(Long id, ProjectDto dto) {
        Optional<ProjectEntity> existEntity = repository.findById(id);
        if(existEntity.isEmpty()) {
            throw new EntityNotFoundException("Project not found with id :" + id);
        }
        ProjectEntity entity = existEntity.get();

        entity.setTitle(dto.getTitle());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setStatus(dto.getStatus());

        ProjectEntity updatedProject = repository.save(entity);
        return convertProjectToDTO(updatedProject);
    }
 // Delete project
    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException("Project not found with id : " + id) ;
        }
        repository.deleteById(id);
    }

}
