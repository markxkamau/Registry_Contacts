package com.example.company.seeder;

import com.example.company.company.Company;
import com.example.company.company.CompanyRepository;
import com.example.company.contact.Contact;
import com.example.company.contact.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CompanyRepository companyRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public DataSeeder(CompanyRepository companyRepository, ContactRepository contactRepository) {
        this.companyRepository = companyRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (companyRepository.count() == 0) {
            System.out.println("Seeding database with sample companies and contacts...");

            // 1. Tech Corp
            Company techCorp = companyRepository.save(new Company("Tech Corp"));
            contactRepository.save(new Contact(5551234001L, techCorp.getId()));
            contactRepository.save(new Contact(5551234002L, techCorp.getId()));

            // 2. Global Industries
            Company globalInd = companyRepository.save(new Company("Global Industries"));
            contactRepository.save(new Contact(5559876001L, globalInd.getId()));

            // 3. Startup Inc
            Company startupInc = companyRepository.save(new Company("Startup Inc"));
            contactRepository.save(new Contact(5554321001L, startupInc.getId()));
            contactRepository.save(new Contact(5554321002L, startupInc.getId()));
            contactRepository.save(new Contact(5554321003L, startupInc.getId()));

            // 4. Acme Solutions
            Company acmeSol = companyRepository.save(new Company("Acme Solutions"));
            contactRepository.save(new Contact(5551112222L, acmeSol.getId()));

            // 5. Zenith Enterprise
            Company zenithEnt = companyRepository.save(new Company("Zenith Enterprise"));
            contactRepository.save(new Contact(5559998888L, zenithEnt.getId()));

            System.out.println("Seeding completed successfully.");
        }
    }
}
