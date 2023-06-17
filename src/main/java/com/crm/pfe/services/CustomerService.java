package com.crm.pfe.services;

import com.crm.pfe.entities.Contact;
import com.crm.pfe.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Page<Customer> getAllCustomers(Pageable pageable);

    Customer getCustomerById(Long id);

    List<Contact> getAllContactsByCustomerId(Long id);
    Customer updateCustomer(Long id, Customer customer);

    String deleteCustomer(Long id);
}
