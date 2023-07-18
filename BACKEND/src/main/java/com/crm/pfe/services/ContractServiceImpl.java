package com.crm.pfe.services;

import com.crm.pfe.entities.Company;
import com.crm.pfe.entities.Contact;
import com.crm.pfe.entities.Contract;
import com.crm.pfe.entities.Customer;
import com.crm.pfe.repository.CompanyRepository;
import com.crm.pfe.repository.ContractRepository;
import com.crm.pfe.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {
    public final ContractRepository contractRepository;
    public final CustomerRepository customerRepository;
    public final CompanyRepository companyRepository;

    @Override
    public Contract createContract(Long idCompany, Long idCustomer, Contract contract) {
        Customer customer = customerRepository.findById(idCustomer).orElseThrow(()->new RuntimeException("Customer Not Found!!"));
        Company company = companyRepository.findById(idCompany).orElseThrow(()->new RuntimeException("Company not found!!!"));
        contract.setCustomer(customer);
        contract.setCompany(company);
        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
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
