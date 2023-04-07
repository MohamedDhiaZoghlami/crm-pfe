package com.crm.pfe.controllers;

import com.crm.pfe.entities.Contact;
import com.crm.pfe.services.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@AllArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping("create/{customerId}")
    public Contact createContact(@PathVariable Long customerId, @RequestBody Contact contact) {
        return contactService.createContact(customerId,contact);
    }

    @GetMapping("/all")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    @PutMapping("/update/{id}")
    public Contact updateContact(@PathVariable Long id,@RequestBody Contact contact) {
        return contactService.updateContact(id,contact);
    }

    @DeleteMapping("delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        return contactService.deleteContact(id);
    }
}
