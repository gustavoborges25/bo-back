package com.gusgo.bo.service;

import com.gusgo.bo.dto.request.EmailRequestDTO;
import com.gusgo.bo.entity.Email;
import com.gusgo.bo.entity.Person;
import com.gusgo.bo.repository.EmailRepository;
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
