package com.gusgo.bo.business.registration;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.gusgo.bo.entity.registration.Email;
import com.gusgo.bo.exception.ServiceException;
import com.gusgo.bo.repository.registration.EmailRepository;

@Component
public class EmailBusiness {
	
    private final EmailRepository emailRepository;

    public EmailBusiness(EmailRepository emailRepository) {
    	this.emailRepository = emailRepository;
    }
	
    public Email findById(UUID id) {
        return emailRepository.findById(id).orElseThrow(() -> new ServiceException("BO-5"));
    }

}
