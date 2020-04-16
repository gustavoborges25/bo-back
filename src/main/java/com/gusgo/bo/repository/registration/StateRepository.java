package com.gusgo.bo.repository.registration;

import com.gusgo.bo.entity.registration.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StateRepository extends JpaRepository<State, UUID> {

    Optional<State> findByIbgeId(int ibgeId);

}
