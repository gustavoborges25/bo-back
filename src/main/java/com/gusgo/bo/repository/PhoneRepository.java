package com.gusgo.bo.repository;

import com.gusgo.bo.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {
}
