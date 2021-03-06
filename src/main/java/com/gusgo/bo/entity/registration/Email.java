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
@Table(name = "bo_email")
public class Email extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id" )
    private Person person;


}
