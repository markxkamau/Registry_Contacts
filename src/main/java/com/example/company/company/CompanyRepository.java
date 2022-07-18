package com.example.company.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "select * from company s where s.name like %:keyword% ", nativeQuery = true)
    Optional<Company> findByName(@Param("keyword") String keyword);

//    Optional<Company> findByName(String keyword);
}
