package com.descodeuses.planit.entity;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.descodeuses.planit.dto.ProjectDto;
import com.descodeuses.planit.service.ProjectService;

@RequestMapping("/api/project")
@Controller

public class ProjectController {
     private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service=service;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAll() {
        List<ProjectDto> items = service.getAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getById( @PathVariable Long id) {
        ProjectDto dto = service.getProjectById(id);
        return new ResponseEntity<ProjectDto>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> create(@RequestBody ProjectDto dto){
        ProjectDto createdDto = service.create(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> update(@PathVariable Long id, @RequestBody ProjectDto dto) {
        ProjectDto updatedDto = service.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
