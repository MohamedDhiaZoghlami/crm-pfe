package com.crm.pfe.services;

import com.crm.pfe.entities.Bill;
import com.crm.pfe.entities.Project;
import com.crm.pfe.repository.BillRepository;
import com.crm.pfe.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService{
    public final BillRepository billRepository;
    public final ProjectRepository projectRepository;

    @Override
    public Bill createBill(Long id,Bill bill) {
        Project project = projectRepository.findById(id).orElseThrow(()->new RuntimeException("Project not found"));
        bill.setProject(project);
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElseThrow(()->new RuntimeException("Bill not found"));
    }

    @Override
    public Bill updateBill(Long id, Bill bill) {
        return billRepository.findById(id).map(b->{
            b.setStep(bill.getStep());
            b.setDescription(bill.getDescription());
            b.setLabel(bill.getLabel());
            return billRepository.save(b);
        }).orElseThrow(()->new RuntimeException("Bill not found"));
    }

    @Override
    public String deleteBill(Long id) {
        billRepository.deleteById(id);
        return "bill deleted";
    }

}
