package com.crm.pfe.controllers;

import com.crm.pfe.entities.Contract;
import com.crm.pfe.services.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract")
@AllArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @PostMapping("create/{idCustomer}/{idOpp}")
    public Contract createContract(@PathVariable Long idCustomer,@PathVariable Long idOpp, @RequestBody Contract contract) {
        return contractService.createContract(idCustomer,idOpp, contract);
    }

    @GetMapping("/all")
    public Page<Contract> getAllContracts(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page,10);
        return contractService.getAllContracts(pageable);
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
