package com.crm.pfe.services;

import com.crm.pfe.entities.Customer;
import com.crm.pfe.entities.Project;
import com.crm.pfe.repository.CustomerRepository;
import com.crm.pfe.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    public final ProjectRepository projectRepository;
    public final CustomerRepository customerRepository;
    @Override
    public Project createProject(Long id, Project project) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer not found"));
        project.setCustomer(customer);
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(()->new RuntimeException("Project not found"));
    }

    @Override
    public Project updateProject(Long id, Project project) {
        return projectRepository.findById(id).map(p->{
            p.setName(project.getName());
            p.setDescription(project.getDescription());
            p.setStart_date(project.getStart_date());
            p.setEnd_date(project.getEnd_date());
            return projectRepository.save(p);
        }).orElseThrow(()->new RuntimeException("Project not found"));
    }

    @Override
    public String deleteProject(Long id) {
        projectRepository.deleteById(id);
        return "Project deleted";
    }
}
