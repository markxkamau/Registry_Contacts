package com.example.company.contact;

import com.example.company.company.Company;
import com.example.company.company.CompanyService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/contact")
public class ContactController {
    private ContactService contactService;
    @Autowired
    private CompanyService companyService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Contact> getContactInfo(@PathVariable Long id) {
        return contactService.getContactInfo(id);
    }

    @GetMapping("/company/{companyId}")
    @ResponseBody
    public Long getContactById(@PathVariable Long companyId){
        return contactService.getContactByCompanyId(companyId).get().getNumber();
    }

    @GetMapping("/new_contact")
    public String getContact(@NotNull Model model) {
        model.addAttribute("contact_info", new Contact());
        return "contact_web";
    }

    @GetMapping("/all_contacts")
    public String viewAllContacts(@ModelAttribute Contact contact,@NotNull Model model){
        model.addAttribute("company_data",companyService.getCompanies());
        model.addAttribute("contact_data",contactService.getContacts());
//        model.addAttribute("contact_data",contactService.getContactByCompanyId(contact.getCompanyId()));
//        model.addAttribute("each_contact",contactService.getContactByCompanyId(contact.getCompanyId()).get().getNumber());
        return "contact_listing";
    }

    @PostMapping
    public void addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
    }

    @PostMapping("/new_contact")
    public String viewContacts(@ModelAttribute Contact contact, @NotNull Model model) {
        model.addAttribute("contact_info", contact);
        model.addAttribute("company_info", companyService.getCompanyById(contact.getCompanyId()).stream().findFirst().get());
        addContact(contact);
        return "contact_confirm";
    }


    @DeleteMapping("/{id}")
    public void deleteContactById(@PathVariable Long id) {
        contactService.deleteContactById(id);
    }

}
