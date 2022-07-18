package com.example.company.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public ResponseEntity getAllCompanies() {
        return ResponseEntity.ok(companyRepository.findAll());
    }

    public List<Company> getCompanies(){
        return  companyRepository.findAll();
    }
    public void addNewCompany(Company company) {
        companyRepository.save(company);
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Optional<Company> getCompanyInfo(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (!company.isPresent()) {
            throw new IllegalStateException("Company Id: " + id + " not yet Allocated");
        }
        return company;
    }

    public void deleteCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (!company.isPresent()) {
            throw new IllegalStateException("Company does not exist");
        }
        companyRepository.deleteById(id);
    }

    public void deleteAllCompanies() {
        companyRepository.deleteAll();
    }

    public Optional<Company> getCompanyByName(String keyword) {
        return companyRepository.findByName(keyword);
    }
}
