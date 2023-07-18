package com.crm.pfe.services;

import com.crm.pfe.entities.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Long id,Project project);

    List<Project> getAllProjects();

    Project getProjectById(Long id);

    Project updateProject(Long id, Project project);

    String deleteProject(Long id);

}
