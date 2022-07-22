package com.example.company.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void deleteContactByCompanyId(Long id) {
        List<Contact> allContact = contactRepository.findAll();
        int x = 0, y = 0;
        List<Long> contactIds = new ArrayList<>();
        while (x < allContact.size()) {
            if (allContact.get(x).getCompanyId().equals(id)) {
                contactIds.add(allContact.get(x).getId());
            }
            x++;
        }
        while (y < contactIds.size()) {
            contactRepository.deleteById(contactIds.get(y));
            y++;
        }
    }

    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }
}
