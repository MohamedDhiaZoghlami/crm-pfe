package com.crm.pfe.services;

import com.crm.pfe.entities.Company;
import com.crm.pfe.entities.Contact;
import com.crm.pfe.entities.Customer;

import java.util.List;

public interface CompanyService {
    Company createCompany(Company company);


    Company getCompanyById(Long id);

    Company updateCompany(Long id, Company company);

    String deleteCompany(Long id);
}
