package com.crm.pfe.repository;

import com.crm.pfe.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long > {
}
