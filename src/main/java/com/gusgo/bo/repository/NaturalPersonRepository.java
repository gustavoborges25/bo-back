package com.gusgo.bo.repository;

import com.gusgo.bo.entity.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, UUID> {
}
