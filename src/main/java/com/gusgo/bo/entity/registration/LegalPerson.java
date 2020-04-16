package com.gusgo.bo.entity.registration;

import javax.persistence.*;

@Entity
@Table(name = "bo_person_legal")
@PrimaryKeyJoinColumn(name="id")
public class LegalPerson extends Person {
    // PESSOA JURIDICA

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String fantasyName;

    @Column(length = 14, nullable = false, unique = true)
    private String cnpj;

    @Column(length = 30)
    private String ie;

}
