package com.gusgo.bo.repository.registration;

import com.gusgo.bo.entity.registration.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
