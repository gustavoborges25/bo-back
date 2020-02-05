package com.gusgo.bo.entity;

import com.gusgo.bo.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@Table(name = "bo_person")
public abstract class Person extends BaseEntity {

    @OneToMany(mappedBy = "person")
    private List<Address> addresses;

    @OneToMany(mappedBy = "person")
    private List<Phone> phones;

    @OneToMany(mappedBy = "person")
    private List<Email> emails;

    private LocalDate registration_date;

}
