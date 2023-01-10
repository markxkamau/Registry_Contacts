package com.example.company.company;

import com.example.company.contact.Contact;
import com.example.company.contact.ContactService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/company")
public class CompanyController {
    private final CompanyService companyService;
    @Autowired
    private ContactService contactService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity getAllCompanies() {
        return companyService.getAllCompanies();
    }


    @GetMapping("/home/{id}")
    public String addCompanyData(@NotNull Model model, @PathVariable Long id) {
        model.addAttribute("company_info", companyService.getCompanyInfo(id).get());
        return "company_home";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompanyId(@NotNull Model model, @PathVariable Long id) {
        model.addAttribute("company_info", companyService.getCompanyInfo(id).get());
        return "company_delete";
    }

    @GetMapping("/new_company")
    public String getCompany(@NotNull Model model) {
        model.addAttribute("company_info", new Company());
        return "company_web";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Company> getCompanyInfo(@PathVariable Long id) {
        return companyService.getCompanyInfo(id);
    }

    @GetMapping("/search")
    public String searchCompanyByKeyword(Company company, Model model, String keyword) {
        model.addAttribute("company_data", companyService.getSearchData(keyword));
        model.addAttribute("contact_data", contactService.getContacts());
        return "contact_listing";
    }

    @PostMapping
    public void addNewCompany(@RequestBody Company company) {
        companyService.addNewCompany(company);
    }

    @PostMapping("/delete/{id}")
    public String deleteCompanyFollowUp(@NotNull Model model, @PathVariable Long id) {
        deleteCompanyById(id);
        model.addAttribute("company_data", companyService.getCompanies());
        model.addAttribute("contact_data", contactService.getContacts());
        return "contact_listing";
    }

    @PostMapping("/new_company")
    public String viewCompanyData(@ModelAttribute Company company, @NotNull Model model) {
        model.addAttribute("company_info", company);
        if (companyService.newCompanyResponse(company) != true) {
            model.addAttribute("company_alert", "Company already exists, Please try again");
            return "company_web";
        }
        addNewCompany(company);
        return "company_home";
    }

    @DeleteMapping
    public void deleteAllCompanies() {
        companyService.deleteAllCompanies();
    }

    public void deleteCompanyById(Long id) {
        companyService.deleteCompanyById(id);
        contactService.deleteContactByCompanyId(id);
    }
}
