package com.gusgo.bo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "bo_person_natural")
@PrimaryKeyJoinColumn(name="id")
public class NaturalPerson extends Person{
    // PESSOA FISICA

    @Column(nullable = false)
    private String name;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(length = 30)
    private String rg;

}
