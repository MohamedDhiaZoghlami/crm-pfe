package com.crm.pfe.repository;

import com.crm.pfe.entities.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {

    @Query("SELECT o FROM Opportunity o WHERE o.stage = 'new'")
    List<Opportunity> findAllNewOpportunities();
}
