package com.crm.pfe.controllers;

import com.crm.pfe.entities.Opportunity;
import com.crm.pfe.services.OpportunityService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opportunity")
@AllArgsConstructor
public class OpportunityController {
    private final OpportunityService opportunityService;

    @PostMapping("/create/{idCustomer}")
    public Opportunity createOpportunity(@PathVariable Long idCustomer, @RequestBody Opportunity opportunity) {
        return opportunityService.createOpportunity(idCustomer, opportunity);
    }

    @GetMapping("/all")
    public Page<Opportunity> getAllOpportunities(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page,10);

        return opportunityService.getAllOpportunities(pageable);
    }

    @GetMapping("/once")
    public List<Opportunity> getAllAtonce() {
        return opportunityService.getAllOpportnitiesOnce();
    }

    @GetMapping("/recentlyAdded")
    public List<Opportunity> getNewAddedOpp() {
        return opportunityService.getNewAddedOpportunities();
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
