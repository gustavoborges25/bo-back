package com.gusgo.bo.entity.registration;

import com.gusgo.bo.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bo_state")
public class State extends BaseEntity {

    @Column(precision = 20, nullable = false)
    private int ibgeId;

    @Column(length = 2, nullable = false)
    private String abbreviation;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "state")
    private List<City> cities;

}
