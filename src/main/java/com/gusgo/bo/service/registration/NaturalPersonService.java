package com.gusgo.bo.service.registration;

import com.gusgo.bo.dto.request.registration.AddressRequestDTO;
import com.gusgo.bo.dto.request.registration.EmailRequestDTO;
import com.gusgo.bo.dto.request.registration.NaturalPersonRequestDTO;
import com.gusgo.bo.dto.request.registration.PhoneRequestDTO;
import com.gusgo.bo.dto.response.registration.AddressResponseDTO;
import com.gusgo.bo.dto.response.registration.EmailResponseDTO;
import com.gusgo.bo.dto.response.registration.NaturalPersonResponseDTO;
import com.gusgo.bo.dto.response.registration.PhoneResponseDTO;
import com.gusgo.bo.entity.registration.Address;
import com.gusgo.bo.entity.registration.Email;
import com.gusgo.bo.entity.registration.NaturalPerson;
import com.gusgo.bo.entity.registration.Phone;
import com.gusgo.bo.exception.ServiceException;
import com.gusgo.bo.repository.registration.NaturalPersonRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NaturalPersonService {

    private final NaturalPersonRepository naturalPersonRepository;
    private final AddressService addressService;
    private final EmailService emailService;
    private final PhoneService phoneService;

    public NaturalPersonService(
            NaturalPersonRepository naturalPersonRepository,
            AddressService addressService,
            EmailService emailService,
            PhoneService phoneService
    ) {
        this.naturalPersonRepository = naturalPersonRepository;
        this.addressService = addressService;
        this.emailService = emailService;
        this.phoneService = phoneService;
    }

    @Transactional
    public NaturalPersonResponseDTO save(NaturalPersonRequestDTO naturalPersonRequestDTO) {
        NaturalPerson naturalPerson = requestDTOToEntity(naturalPersonRequestDTO);

//        naturalPerson.setAddresses(getAddressesSaved(naturalPersonRequestDTO.getAddresses(), naturalPerson));
        naturalPerson.setEmails(getEmailsSaved(naturalPersonRequestDTO.getEmails(), naturalPerson));
        naturalPerson.setPhones(getPhonesSaved(naturalPersonRequestDTO.getPhones(), naturalPerson));

        naturalPersonRepository.save(naturalPerson);

        return entityToResponseDTO(naturalPerson);
    }

    public List<NaturalPersonResponseDTO> getAll() {
        List<NaturalPerson> naturalPeople = naturalPersonRepository.findAll();
        List<NaturalPersonResponseDTO> naturalPeopleResponseDTO = new ArrayList<>();
        naturalPeople.forEach(naturalPerson -> naturalPeopleResponseDTO.add(entityToResponseDTO(naturalPerson)));
        return naturalPeopleResponseDTO;
    }

    public NaturalPersonResponseDTO getById(UUID id) {
        NaturalPerson naturalPerson = findById(id);
        return entityToResponseDTO(naturalPerson);
    }

    private NaturalPerson findById(UUID id) {
        return naturalPersonRepository.findById(id).orElseThrow(() -> new ServiceException("BO-3"));
    }

    private NaturalPersonResponseDTO entityToResponseDTO(NaturalPerson naturalPerson) {
        List<AddressResponseDTO> addressesResponse = getAddressesResponseDTO(naturalPerson.getAddresses());
        List<EmailResponseDTO> emailsResponse = getPhonesResponseDTO(naturalPerson.getEmails());
        List<PhoneResponseDTO> phonesResponse = getEmailsResponseDTO(naturalPerson.getPhones());

        return NaturalPersonResponseDTO.builder()
                .id(naturalPerson.getId())
                .cpf(naturalPerson.getCpf())
                .name(naturalPerson.getName())
                .rg(naturalPerson.getRg())
                .addresses(addressesResponse)
                .emails(emailsResponse)
                .phones(phonesResponse)
                .build();
    }

    private List<AddressResponseDTO> getAddressesResponseDTO(List<Address> addresses) {
        List<AddressResponseDTO> addressesResponse = new ArrayList<>();
        addresses.forEach(address -> addressesResponse.add(AddressResponseDTO.builder().id(address.getId()).cep(address.getCep()).build()));
        return addressesResponse;
    }

    private List<EmailResponseDTO> getPhonesResponseDTO(List<Email> emails) {
        List<EmailResponseDTO> emailsResponse = new ArrayList<>();
        emails.forEach((email -> emailsResponse.add(EmailResponseDTO.builder().id(email.getId()).email(email.getEmail()).build())));
        return emailsResponse;
    }

    private List<PhoneResponseDTO> getEmailsResponseDTO(List<Phone> phones) {
        List<PhoneResponseDTO> phonesResponse = new ArrayList<>();
        phones.forEach(phone -> phonesResponse.add(PhoneResponseDTO.builder().id(phone.getId()).number(phone.getNumber()).build()));
        return phonesResponse;
    }

//    @NotNull
//    private List<Address> getAddressesSaved(@NotNull List<AddressRequestDTO> addressesRequestDTO, NaturalPerson naturalPerson) {
//        List<Address> addresses = new ArrayList<>();
//        addressesRequestDTO.forEach(address -> addresses.add(addressService.save(address, naturalPerson)));
//        return addresses;
//    }

    @NotNull
    private List<Email> getEmailsSaved(@NotNull List<EmailRequestDTO> emailRequestDTOS, NaturalPerson naturalPerson) {
        List<Email> emails = new ArrayList<>();
        emailRequestDTOS.forEach(email -> emails.add(emailService.save(email, naturalPerson)));
        return emails;
    }

    @NotNull
    private List<Phone> getPhonesSaved(@NotNull List<PhoneRequestDTO> phoneRequestDTOS, NaturalPerson naturalPerson) {
        List<Phone> phones = new ArrayList<>();
        phoneRequestDTOS.forEach(phone -> phones.add(phoneService.save(phone, naturalPerson)));
        return phones;
    }

    private NaturalPerson requestDTOToEntity(NaturalPersonRequestDTO naturalPersonRequestDTO) {
        return NaturalPerson.builder()
                .name(naturalPersonRequestDTO.getName())
                .cpf(naturalPersonRequestDTO.getCpf())
                .rg(naturalPersonRequestDTO.getRg())
                .registration_date(LocalDate.now())
                .build();
    }

}
