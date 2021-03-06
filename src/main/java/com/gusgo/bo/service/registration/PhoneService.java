package com.gusgo.bo.service.registration;

import com.gusgo.bo.dto.request.registration.PhoneRequestDTO;
import com.gusgo.bo.entity.registration.Person;
import com.gusgo.bo.entity.registration.Phone;
import com.gusgo.bo.repository.registration.PhoneRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneService (PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Transactional
    public Phone save(PhoneRequestDTO phoneRequestDTO, Person person) {
        Phone phone = Phone.builder()
                .number(phoneRequestDTO.getNumber())
                .person(person)
                .build();
        return phoneRepository.save(phone);
    }

}
