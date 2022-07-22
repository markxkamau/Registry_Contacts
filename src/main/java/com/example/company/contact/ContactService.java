package com.example.company.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ResponseEntity getAllContacts() {
        return ResponseEntity.ok(contactRepository.findAll());
    }

    public Optional<Contact> getContactInfo(Long id) {
        return contactRepository.findById(id);
    }

    public boolean confirmContact(Contact contact) {
        int x = 0;
        List<Contact> contacts = contactRepository.findAll();
        while (x < contacts.size()) {
            if (contact.getNumber().equals(contacts.get(x).getNumber())) {
                return false;
            }
            x++;
        }
        return true;
    }

    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }


    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactByCompanyId(Long companyId) {
        return contactRepository.findByCompanyId(companyId);
    }

}
