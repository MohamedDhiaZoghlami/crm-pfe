package com.crm.pfe.controllers;

import com.crm.pfe.entities.Bill;
import com.crm.pfe.services.BillService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
@AllArgsConstructor
public class BillController {
    private final BillService billService;

    @PostMapping("/create/{idProject}")
    public Bill createBill(@PathVariable Long idProject, @RequestBody Bill bill) {
        return billService.createBill(idProject,bill);
    }

    @GetMapping("/all")
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @PutMapping("/update/{id}")
    public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        return billService.updateBill(id,bill);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBill(@PathVariable Long id) {
        return billService.deleteBill(id);
    }

}
