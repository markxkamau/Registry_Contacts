package com.example.company.contact;

import com.example.company.company.CompanyService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/registry")
public class ContactController {
    private ContactService contactService;
    @Autowired
    private CompanyService companyService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact/all_contacts")
    public ResponseEntity getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/contact/{id}")
    @ResponseBody
    public Optional<Contact> getContactInfo(@PathVariable Long id) {
        return contactService.getContactInfo(id);
    }

    @GetMapping("/company/contact/{companyId}")
    @ResponseBody
    public Long getContactById(@PathVariable Long companyId){
        return contactService.getContactByCompanyId(companyId).get().getNumber();
    }

    @GetMapping("/new_contact")
    public String getContact(@NotNull Model model) {
        model.addAttribute("contact_info", new Contact());
        return "Contact/contact_web";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable Long id){
        contactService.deleteContactById(id);
        return "redirect:/registry/all_contacts";
    }

    @GetMapping("/all_contacts")
    public String viewAllContacts(@ModelAttribute Contact contact,@NotNull Model model){
        model.addAttribute("company_data",companyService.getCompanies());
        model.addAttribute("contact_data",contactService.getContacts());
        return "Contact/contact_listing";
    }

    @PostMapping("/add_contact")
    public void addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
    }

    @PostMapping("/new_contact")
    public String viewContacts(@ModelAttribute Contact contact, @NotNull Model model) {
        model.addAttribute("contact_info", contact);
        model.addAttribute("company_info", companyService.getCompanyById(contact.getCompanyId()).get());
        if (contactService.confirmContact(contact) != true){
            model.addAttribute("contact_alert","Contact already exists, Please try again");
            return "Company/company_home";
        }
        addContact(contact);
        return "Contact/contact_confirm";
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteContactById(@PathVariable Long id) {
        contactService.deleteContactById(id);
    }

}
