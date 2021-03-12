package com.gusgo.bo.entity.registration;

import com.gusgo.bo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bo_phone")
public class Phone extends BaseEntity {

    @Column(length = 30, nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id" )
    private Person person;

}
