package com.example.company.contact;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    Optional<Contact> findByCompanyId(Long companyId);
}
