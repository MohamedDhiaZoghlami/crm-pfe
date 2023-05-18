package com.crm.pfe.controllers;

import com.crm.pfe.entities.Company;
import com.crm.pfe.entities.Contact;
import com.crm.pfe.entities.Customer;
import com.crm.pfe.services.CompanyService;
import com.crm.pfe.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/create")
    public Company createCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }


    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PutMapping("/update/{id}")
    public Company updateCompany(@RequestBody Company company, @PathVariable Long id) {
        return companyService.updateCompany(id,company);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id) {
        return companyService.deleteCompany(id);
    }
}
