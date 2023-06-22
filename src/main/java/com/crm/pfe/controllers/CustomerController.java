package com.crm.pfe.controllers;

import com.crm.pfe.entities.Contact;
import com.crm.pfe.entities.Customer;
import com.crm.pfe.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@Validated @RequestBody Customer customer) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/customers/create").toUriString());
        return ResponseEntity.created(uri).body(customerService.createCustomer(customer));
    }

    @GetMapping("/Once")
    public List<Customer> getAllCustomersOnce() {
        return customerService.getAllCustomersOnce();
    }
    @GetMapping("/all")
    public Page<Customer> getAllCustomers(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page,10);
        return customerService.getAllCustomers(pageable);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/contacts/{id}")
    public List<Contact> getAllContactsByCustomerId(@PathVariable Long id) {
        return customerService.getAllContactsByCustomerId(id);
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
