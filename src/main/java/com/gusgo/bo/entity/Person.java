package com.gusgo.bo.entity;

import com.gusgo.bo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
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
