package com.crm.pfe.services;

import com.crm.pfe.entities.*;
import com.crm.pfe.repository.CompanyRepository;
import com.crm.pfe.repository.ContractRepository;
import com.crm.pfe.repository.CustomerRepository;
import com.crm.pfe.repository.OpportunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {
    public final ContractRepository contractRepository;
    public final CustomerRepository customerRepository;
    public final OpportunityRepository opportunityRepository;

    @Override
    public Contract createContract( Long idCustomer,Long idOpp ,Contract contract) {
        Customer customer = customerRepository.findById(idCustomer).orElseThrow(()->new RuntimeException("Customer Not Found!!"));
        contract.setCustomer(customer);
        Opportunity opportunity = opportunityRepository.findById(idOpp).orElseThrow(()->new RuntimeException("Opportunity Not Found!!"));
        contract.setOpportunity(opportunity);
        return contractRepository.save(contract);
    }

    @Override
    public Page <Contract> getAllContracts(Pageable pageable) {

        return contractRepository.findAll(pageable);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id).orElseThrow(()->new RuntimeException("Contract cannot be found. "));
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        return contractRepository.findById(id).map(c->{
            c.setDescription(contract.getDescription());
            c.setFile(contract.getFile());
            return contractRepository.save(c);
        }).orElseThrow(()->new RuntimeException("Contract not found"));
    }

    @Override
    public String deleteContract(Long id) {
        contractRepository.deleteById(id);
        return "Contract deleted";
    }
}
