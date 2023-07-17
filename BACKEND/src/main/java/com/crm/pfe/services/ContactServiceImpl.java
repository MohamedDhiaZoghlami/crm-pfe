package com.crm.pfe.services;

import com.crm.pfe.entities.Contact;
import com.crm.pfe.entities.Customer;
import com.crm.pfe.repository.ContactRepository;
import com.crm.pfe.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements  ContactService{
    public final ContactRepository contactRepository;
    public final CustomerRepository customerRepository;

    @Override
    public Contact createContact(Long id, Contact contact) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        contact.setCustomer(customer);
        return contactRepository.save(contact);
    }

    @Override
    public Page<Contact> getAllContacts(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact Not Found"));
    }

    @Override
    public Contact updateContact(Long id,  Contact contact) {
        return contactRepository.findById(id).map(c->{
           c.setFirstName(contact.getFirstName());
           c.setLastName(contact.getLastName());
           c.setEmail(contact.getEmail());
           c.setPhone(contact.getPhone());
           c.setCustomer(contact.getCustomer());
           c.setCreated_at(contact.getCreated_at());
           c.setCreated_By(contact.getCreated_By());
           c.setLast_updated_By(contact.getLast_updated_By());
           c.setLast_updated_at(contact.getLast_updated_at());
           return contactRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Contact Not Found"));
    }

    @Override
    public String deleteContact(Long id) {
        contactRepository.deleteById(id);
        return "Contact deleted";
    }
}
