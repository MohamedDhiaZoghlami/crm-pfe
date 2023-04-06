package com.crm.pfe.controllers;

import com.crm.pfe.entities.Customer;
import com.crm.pfe.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/update/{id}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        return customerService.updateCustomer(id,customer);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }
}
