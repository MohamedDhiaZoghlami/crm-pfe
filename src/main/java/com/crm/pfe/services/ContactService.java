package com.crm.pfe.services;

import com.crm.pfe.entities.Contact;

import java.util.List;

public interface ContactService {
    Contact createContact(Long id, Contact contact);

    List<Contact> getAllContacts();

    Contact getContactById(Long id);


    Contact updateContact(Long id, Contact contact);

    String deleteContact(Long id);


}
