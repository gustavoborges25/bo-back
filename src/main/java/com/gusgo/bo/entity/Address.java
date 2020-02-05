package com.gusgo.bo.entity;

import com.gusgo.bo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bo_address")
public class Address extends BaseEntity {

    @Column(nullable = false)
    private String street;

    @Column(length = 20, nullable = false)
    private String number;

    @Column
    private String complement;

    @Column(length = 8, nullable = false)
    private String cep;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String type;

    @OneToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    private Person person;

}
