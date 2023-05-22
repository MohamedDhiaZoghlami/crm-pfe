package com.crm.pfe.services;

import com.crm.pfe.entities.Bill;

import java.util.List;

public interface BillService {
    Bill createBill(Long id, Bill bill);

    List<Bill> getAllBills();

    Bill getBillById(Long id);

    Bill updateBill(Long id, Bill bill);

    String deleteBill(Long id);
}
