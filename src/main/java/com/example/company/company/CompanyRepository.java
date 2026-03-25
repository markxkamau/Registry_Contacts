package com.example.company.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    // In your Repository interface
    List<Company> findByNameContaining(String keyword);

    boolean existsByNameIgnoreCase(String name);
}
