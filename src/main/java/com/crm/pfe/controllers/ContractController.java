package com.crm.pfe.controllers;

import com.crm.pfe.entities.Contract;
import com.crm.pfe.services.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract")
@AllArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @PostMapping("create/{idCompany}/{idCustomer}")
    public Contract createContract(@PathVariable Long idCompany,@PathVariable Long idCustomer, @RequestBody Contract contract) {
        return contractService.createContract(idCompany,idCustomer,contract);
    }

    @GetMapping("/all")
    public List<Contract> getAllContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping("/{id}")
    public Contract getContractById(@PathVariable Long id) {
        return contractService.getContractById(id);
    }

    @PutMapping("/update/{id}")
    public Contract updateContract(@PathVariable Long id,@RequestBody Contract contract) {
        return contractService.updateContract(id,contract);
    }

    @DeleteMapping("delete/{id}")
    public String deleteContract(@PathVariable Long id) {
        return contractService.deleteContract(id);
    }
}
