package com.example.company.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByCompanyId(Long companyId);

    boolean existsByNumber(Long number);

    @Transactional
    @Modifying
    void deleteByCompanyId(Long companyId);
}
