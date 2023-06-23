package com.crm.pfe.services;

import com.crm.pfe.entities.Contact;
import com.crm.pfe.entities.Customer;
import com.crm.pfe.entities.Opportunity;
import com.crm.pfe.repository.ContactRepository;
import com.crm.pfe.repository.CustomerRepository;
import com.crm.pfe.repository.OpportunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpportunityServiceImpl implements OpportunityService{
    public final OpportunityRepository opportunityRepository;
    public final CustomerRepository customerRepository;
    public final ContactRepository contactRepository;

    @Override
    public Opportunity createOpportunity(Long idCustomer, Long idContact, Opportunity opportunity) {
        Customer customer = customerRepository.findById(idCustomer).orElseThrow(()->new RuntimeException("Customer not found"));
        Contact contact = contactRepository.findById(idContact).orElseThrow(()->new RuntimeException("Contact not found"));
        opportunity.setCustomer(customer);
        opportunity.setContact(contact);
        return opportunityRepository.save(opportunity);
    }

    @Override
    public List<Opportunity> getAllOpportunities() {
        return opportunityRepository.findAll();
    }

    @Override
    public Opportunity getOpportunityById(Long id) {
        return opportunityRepository.findById(id).orElseThrow(()->new RuntimeException("opp not found"));
    }

    @Override
    public Opportunity updateOpportunity(Long id, Opportunity opportunity) {
        return opportunityRepository.findById(id).map(o->{
            o.setName(opportunity.getName());
            o.setDescription(opportunity.getDescription());
            o.setFile(opportunity.getFile());
            o.setExpected_close_date(opportunity.getExpected_close_date());
            o.setValue(opportunity.getValue());
            o.setStage(opportunity.getStage());
            o.setCreated_at(opportunity.getCreated_at());
            o.setCreated_By(opportunity.getCreated_By());
            o.setLast_updated_By(opportunity.getLast_updated_By());
            o.setLast_updated_at(opportunity.getLast_updated_at());
            return opportunityRepository.save(o);
        }).orElseThrow(()->new RuntimeException("opp not found"));
    }

    public String deleteOpportunity(Long id) {
        opportunityRepository.deleteById(id);
        return "Opp deleted :D";
    }
}
