package com.crm.pfe.controllers;

import com.crm.pfe.entities.Opportunity;
import com.crm.pfe.services.OpportunityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opportunity")
@AllArgsConstructor
public class OpportunityController {
    private final OpportunityService opportunityService;

    @PostMapping("/create/{idCustomer}/{idContact}")
    public Opportunity createOpportunity(@PathVariable Long idCustomer, @PathVariable Long idContact, @RequestBody Opportunity opportunity) {
        return opportunityService.createOpportunity(idCustomer,idContact,opportunity);
    }

    @GetMapping("/all")
    public List<Opportunity> getAllOpportunities() {
        return opportunityService.getAllOpportunities();
    }

    @GetMapping("/{id}")
    public Opportunity getOpportunityById(@PathVariable Long id) {
        return opportunityService.getOpportunityById(id);
    }

    @PutMapping("/update/{id}")
    public Opportunity updateOpportunity(@PathVariable Long id, @RequestBody Opportunity opportunity) {
        return opportunityService.updateOpportunity(id,opportunity);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOpportunity(@PathVariable Long id) {
        return opportunityService.deleteOpportunity(id);
    }
}
