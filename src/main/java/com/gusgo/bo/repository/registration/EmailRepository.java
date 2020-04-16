package com.gusgo.bo.repository.registration;

import com.gusgo.bo.entity.registration.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
