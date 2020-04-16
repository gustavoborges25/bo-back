package com.gusgo.bo.repository.registration;

import com.gusgo.bo.entity.registration.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {
}
