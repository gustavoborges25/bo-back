package com.gusgo.bo.business.registration;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.gusgo.bo.entity.registration.NaturalPerson;
import com.gusgo.bo.exception.ServiceException;
import com.gusgo.bo.repository.registration.NaturalPersonRepository;

@Component
public class NaturalPersonBusiness {

    private final NaturalPersonRepository naturalPersonRepository;

    public NaturalPersonBusiness(NaturalPersonRepository naturalPersonRepository) {
        this.naturalPersonRepository = naturalPersonRepository;
    }
    
    public NaturalPerson findById(UUID id) {
        return naturalPersonRepository.findById(id).orElseThrow(() -> new ServiceException("BO-3"));
    }

	public void save(NaturalPerson naturalPerson) {
		naturalPersonRepository.save(naturalPerson);
	}

	public List<NaturalPerson> findAll() {
		return naturalPersonRepository.findAll();
	}

	public void deleteById(UUID id) {
		naturalPersonRepository.deleteById(id);		
	}

}
