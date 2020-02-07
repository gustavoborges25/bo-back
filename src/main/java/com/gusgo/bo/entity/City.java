package com.gusgo.bo.entity;

import com.gusgo.bo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bo_city")
public class City extends BaseEntity {

    @Column(precision = 20, nullable = false)
    private int ibgeId;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private State state;

}
