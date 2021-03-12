package com.gusgo.bo.entity.registration;

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
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Table(name = "bo_person")
public abstract class Person extends BaseEntity {

    @OneToMany(mappedBy = "person" , cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "person" , cascade = CascadeType.ALL)
    private List<Phone> phones;

    @OneToMany(mappedBy = "person" , cascade = CascadeType.ALL)
    private List<Email> emails;

    private LocalDate registrationDate;
    
    private LocalDate updateDate;


}
