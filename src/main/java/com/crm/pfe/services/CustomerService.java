package com.crm.pfe.services;

import com.crm.pfe.entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer updateCustomer(Long id, Customer customer);

    String deleteCustomer(Long id);
}
