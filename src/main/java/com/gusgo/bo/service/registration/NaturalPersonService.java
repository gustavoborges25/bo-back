package com.gusgo.bo.service.registration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.gusgo.bo.business.registration.AddressBusiness;
import com.gusgo.bo.business.registration.CityBusiness;
import com.gusgo.bo.business.registration.EmailBusiness;
import com.gusgo.bo.business.registration.NaturalPersonBusiness;
import com.gusgo.bo.business.registration.PhoneBusiness;
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

@Service
public class NaturalPersonService {

    private final NaturalPersonBusiness naturalPersonBusiness; 
    private final AddressBusiness addressBusiness;
    private final EmailBusiness emailBusiness;
    private final PhoneBusiness phoneBusiness;
    private final CityBusiness cityBusiness;

    public NaturalPersonService(
            NaturalPersonBusiness naturalPersonBusiness,
            AddressBusiness addressBusiness,
            EmailBusiness emailBusiness,
            PhoneBusiness phoneBusiness,
            CityBusiness cityBusiness
    ) {
        this.naturalPersonBusiness = naturalPersonBusiness;
        this.addressBusiness = addressBusiness;
        this.cityBusiness = cityBusiness;
        this.emailBusiness = emailBusiness;
        this.phoneBusiness = phoneBusiness;
    }

    @Transactional
    public NaturalPersonResponseDTO save(NaturalPersonRequestDTO naturalPersonRequestDTO) {
        NaturalPerson naturalPerson = requestDTOToEntity(null, naturalPersonRequestDTO);

        naturalPersonBusiness.save(naturalPerson);

        return entityToResponseDTO(naturalPerson);
    }

    @Transactional
    public NaturalPersonResponseDTO update(UUID id, NaturalPersonRequestDTO naturalPersonRequestDTO) {
        NaturalPerson naturalPerson = requestDTOToEntity(id, naturalPersonRequestDTO);

        naturalPersonBusiness.save(naturalPerson);
        
		return entityToResponseDTO(naturalPerson);
	}

    @Transactional
	public void delete(UUID id) {
    	naturalPersonBusiness.deleteById(id);
	}


    public List<NaturalPersonResponseDTO> getAll() {
        List<NaturalPerson> naturalPeople = naturalPersonBusiness.findAll();
        List<NaturalPersonResponseDTO> naturalPeopleResponseDTO = new ArrayList<>();
        naturalPeople.forEach(naturalPerson -> naturalPeopleResponseDTO.add(entityToResponseDTO(naturalPerson)));
        return naturalPeopleResponseDTO;
    }

    public NaturalPersonResponseDTO getById(UUID id) {
        NaturalPerson naturalPerson = naturalPersonBusiness.findById(id);
        return entityToResponseDTO(naturalPerson);
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
        addresses.forEach(address -> addressesResponse.add(
        		AddressResponseDTO.builder()
        			.id(address.getId())
        			.street(address.getStreet())
        			.number(address.getNumber())
        			.complement(address.getComplement())
        			.cep(address.getCep())
        			.neighborhood(address.getNeighborhood())
        			.type(address.getType())
        			.cityId(address.getCity().getId())        	
        			.build()));
        return addressesResponse;
    }

    private List<EmailResponseDTO> getPhonesResponseDTO(List<Email> emails) {
        List<EmailResponseDTO> emailsResponse = new ArrayList<>();
        emails.forEach((email -> emailsResponse.add(
        		EmailResponseDTO.builder()
        			.id(email.getId())
        			.email(email.getEmail())
        			.build())));
        return emailsResponse;
    }

    private List<PhoneResponseDTO> getEmailsResponseDTO(List<Phone> phones) {
        List<PhoneResponseDTO> phonesResponse = new ArrayList<>();
        phones.forEach(phone -> phonesResponse.add(
        		PhoneResponseDTO.builder()
        			.id(phone.getId())
        			.number(phone.getNumber())
        			.build()));
        return phonesResponse;
    }

    private NaturalPerson requestDTOToEntity(UUID id, NaturalPersonRequestDTO naturalPersonRequestDTO) {
    	NaturalPerson person;

    	if (id != null) {
    		person = naturalPersonBusiness.findById(id);
    		person.setName(naturalPersonRequestDTO.getName());
    		person.setCpf(naturalPersonRequestDTO.getCpf());
    		person.setRg(naturalPersonRequestDTO.getRg());
    		person.setUpdateDate(LocalDate.now());
    	} else {
        	person = NaturalPerson.builder()
        			.name(naturalPersonRequestDTO.getName())
        			.cpf(naturalPersonRequestDTO.getCpf())
        			.rg(naturalPersonRequestDTO.getRg())
        			.registrationDate(LocalDate.now())
        			.build();
    	}
    	
		List<Address> addresses = addressDTOtoEntity(naturalPersonRequestDTO, person);
    	List<Phone> phones = phoneDTOtoEntity(naturalPersonRequestDTO, person);
    	List<Email> emails = emailDTOtoEntity(naturalPersonRequestDTO, person);
    	
    	person.setAddresses(addresses);
    	person.setPhones(phones);
    	person.setEmails(emails);
    	
        return person;
    }

	private List<Address> addressDTOtoEntity(NaturalPersonRequestDTO naturalPersonRequestDTO, NaturalPerson person) {
		List<Address> addresses = new ArrayList<>();

    	naturalPersonRequestDTO.getAddresses().forEach(addressRequestDTO -> {
    		Address address;
        	if (addressRequestDTO.getId() != null) {
        		address = addressBusiness.findById(addressRequestDTO.getId());
        		address.setStreet(addressRequestDTO.getStreet());
        		address.setNumber(addressRequestDTO.getNumber());
        		address.setComplement(addressRequestDTO.getComplement());
        		address.setCep(addressRequestDTO.getCep());
        		address.setNeighborhood(addressRequestDTO.getNeighborhood());
        		address.setType(addressRequestDTO.getType());
        		address.setCity(cityBusiness.findById(addressRequestDTO.getCityId()));        		
			} else {
				address = Address.builder()
	        		.street(addressRequestDTO.getStreet())
	        		.number(addressRequestDTO.getNumber())
	        		.complement(addressRequestDTO.getComplement())
	        		.cep(addressRequestDTO.getCep())
	        		.neighborhood(addressRequestDTO.getNeighborhood())
	        		.type(addressRequestDTO.getType())
	        		.city(cityBusiness.findById(addressRequestDTO.getCityId()))
	        		.person(person)
	        		.build();				
			}      	

        	addresses.add(address);        	
        });
    	
		return addresses;
	}

	private List<Phone> phoneDTOtoEntity(NaturalPersonRequestDTO naturalPersonRequestDTO, NaturalPerson person) {
		List<Phone> phones = new ArrayList<>();
		
		naturalPersonRequestDTO.getPhones().forEach(phoneRequestDTO -> {
    		Phone phone;
        	if (phoneRequestDTO.getId() != null) {
        		phone = phoneBusiness.findById(phoneRequestDTO.getId());
        		phone.setNumber(phoneRequestDTO.getNumber());
			} else {
				phone = Phone.builder()
						.number(phoneRequestDTO.getNumber())
						.person(person)
						.build();
			}      	

        	phones.add(phone);        	
        });
		return phones;
	}
	
	private List<Email> emailDTOtoEntity(NaturalPersonRequestDTO naturalPersonRequestDTO, NaturalPerson person) {
		List<Email> emails = new ArrayList<>();
		
		naturalPersonRequestDTO.getEmails().forEach(emailRequestDTO -> {
    		Email email;
        	if (emailRequestDTO.getId() != null) {
        		email = emailBusiness.findById(emailRequestDTO.getId());
        		email.setEmail(emailRequestDTO.getEmail());
			} else {
				email = Email.builder()
						.email(emailRequestDTO.getEmail())
						.person(person)
						.build();
			}      	

        	emails.add(email);        	
        });
		return emails;
	}

}
