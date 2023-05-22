package com.crm.pfe.services;

import com.crm.pfe.entities.Contract;

import java.util.List;

public interface ContractService {
    Contract createContract(Long idCompany, Long idCustomer, Contract contract);

    List<Contract> getAllContracts();

    Contract getContractById(Long id);

    Contract updateContract(Long id, Contract contract);

    String deleteContract(Long id);
}
