package com.gusgo.bo.service.registration;

import com.gusgo.bo.dto.request.registration.EmailRequestDTO;
import com.gusgo.bo.entity.registration.Email;
import com.gusgo.bo.entity.registration.Person;
import com.gusgo.bo.repository.registration.EmailRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    public EmailService (EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Transactional
    public Email save(EmailRequestDTO emailRequestDTO, Person person) {
        Email email = Email.builder()
                .email(emailRequestDTO.getEmail())
                .person(person)
                .build();
        return emailRepository.save(email);
    }

}
