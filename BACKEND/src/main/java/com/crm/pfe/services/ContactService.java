package com.crm.pfe.services;

import com.crm.pfe.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactService {
    Contact createContact(Long id, Contact contact);

    Page<Contact> getAllContacts(Pageable pageable);

    Contact getContactById(Long id);


    Contact updateContact(Long id, Contact contact);

    String deleteContact(Long id);


}
