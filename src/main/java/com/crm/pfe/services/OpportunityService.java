package com.crm.pfe.services;

import com.crm.pfe.entities.Opportunity;

import java.util.List;

public interface OpportunityService {
    Opportunity createOpportunity(Long idCustomer, Long idContact, Opportunity opportunity);

    List<Opportunity> getAllOpportunities();

    Opportunity getOpportunityById(Long id);

    Opportunity updateOpportunity(Long id, Opportunity opportunity);

    String deleteOpportunity(Long id);
}
