package com.crm.pfe.services;

import com.crm.pfe.entities.Opportunity;
import com.crm.pfe.entities.Quotient;
import com.crm.pfe.repository.OpportunityRepository;
import com.crm.pfe.repository.QuotientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuotientServiceImpl implements QuotientService {
    public final QuotientRepository quotientRepository;
    public final OpportunityRepository opportunityRepository;

    @Override
    public Quotient createQuotient(Long id, Quotient quotient) {
        Opportunity opportunity = opportunityRepository.findById(id).orElseThrow(()->new RuntimeException("Opp not found"));
        quotient.setOpportunity(opportunity);
        return quotientRepository.save(quotient);
    }

    @Override
    public Quotient getQuotientById(Long id) {
        return quotientRepository.findById(id).orElseThrow(()->new RuntimeException("Quotient not found "));
    }

    @Override
    public Quotient updateQuotient(Long id, Quotient quotient) {
        return quotientRepository.findById(id).map(q->{
            q.setType(quotient.getType());
            q.setAmount(quotient.getAmount());
            q.setLabel(quotient.getLabel());
            return quotientRepository.save(q);
        }).orElseThrow(()->new RuntimeException("quotient not found"));
    }

    @Override
    public String deleteQuotient(Long id) {
        quotientRepository.deleteById(id);
        return "Quotient deleted";
    }

}
