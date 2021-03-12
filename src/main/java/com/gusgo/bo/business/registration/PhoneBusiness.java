package com.gusgo.bo.business.registration;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.gusgo.bo.entity.registration.Phone;
import com.gusgo.bo.exception.ServiceException;
import com.gusgo.bo.repository.registration.PhoneRepository;

@Component
public class PhoneBusiness {
	
    private final PhoneRepository phoneRepository;

    public PhoneBusiness(PhoneRepository phoneRepository) {
    	this.phoneRepository = phoneRepository;
    }
	
    public Phone findById(UUID id) {
        return phoneRepository.findById(id).orElseThrow(() -> new ServiceException("BO-6"));
    }

}
