package com.example.company.contact;

import javax.persistence.*;

@Entity
@Table
public class Contact {
    @Id
    @SequenceGenerator(
            sequenceName = "contact_sequence",
            name = "contact_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_sequence"
    )
    private Long id;
    private Long number;
    private Long companyId;

    public Contact() {
    }

    public Contact(Long id, Long number, Long companyId) {
        this.id = id;
        this.number = number;
        this.companyId = companyId;
    }

    public Contact(Long number, Long companyId) {
        this.number = number;
        this.companyId = companyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
