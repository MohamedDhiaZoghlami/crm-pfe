package com.crm.pfe.controllers;

import com.crm.pfe.entities.Project;
import com.crm.pfe.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/create/{idCustomer}")
    public Project createProject(@PathVariable Long idCustomer, @RequestBody Project project) {
        return projectService.createProject(idCustomer,project);
    }

    @GetMapping("/all")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PutMapping("/update/{id}")
    public Project updateProject(@PathVariable Long id, Project project) {
        return projectService.updateProject(id,project);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }
}
