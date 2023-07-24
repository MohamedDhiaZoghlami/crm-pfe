package com.crm.pfe.services;

import com.crm.pfe.entities.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContractService {
    Contract createContract(Long idCustomer,Long idOpp, Contract contract);

    Page <Contract> getAllContracts(Pageable pageable);

    Contract getContractById(Long id);

    Contract updateContract(Long id, Contract contract);

    String deleteContract(Long id);
}
