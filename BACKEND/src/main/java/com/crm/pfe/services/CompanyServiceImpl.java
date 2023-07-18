package com.crm.pfe.services;

import com.crm.pfe.entities.Company;

import com.crm.pfe.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }



    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company Not Found"));
    }


    @Override
    public Company updateCompany(Long id, Company company) {
        return companyRepository.findById(id).map(c->{
            c.setName(company.getName());
            c.setEmail(company.getEmail());
            c.setPhone(company.getPhone());
            c.setAdress(company.getAdress());
            c.setDescription(company.getDescription());
            c.setType(company.getType());
            c.setLogo(company.getLogo());
            return companyRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Company Not Found"));
    }

    @Override
    public String deleteCompany(Long id) {
        companyRepository.deleteById(id);
        return "Company deleted!!";
    }
}
