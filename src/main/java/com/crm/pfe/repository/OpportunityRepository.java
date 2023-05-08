package com.crm.pfe.repository;

import com.crm.pfe.entities.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {
}
