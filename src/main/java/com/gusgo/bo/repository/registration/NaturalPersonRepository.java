package com.gusgo.bo.repository.registration;

import com.gusgo.bo.entity.registration.NaturalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NaturalPersonRepository extends JpaRepository<NaturalPerson, UUID> {
}
