package com.crm.pfe.services;

import com.crm.pfe.entities.Opportunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OpportunityService {
    Opportunity createOpportunity(Long idCustomer, Opportunity opportunity);

    List<Opportunity> getAllOpportnitiesOnce();
    Page<Opportunity> getAllOpportunities(Pageable pageable);

    List<Opportunity> getNewAddedOpportunities();

    Opportunity getOpportunityById(Long id);

    Opportunity updateOpportunity(Long id, Opportunity opportunity);

    String deleteOpportunity(Long id);
}
