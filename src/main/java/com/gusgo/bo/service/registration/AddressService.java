package com.gusgo.bo.service.registration;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gusgo.bo.business.registration.AddressBusiness;
import com.gusgo.bo.dto.response.registration.CityResponseDTO;
import com.gusgo.bo.dto.response.registration.StateResponseDTO;

@Service
public class AddressService {

    private final AddressBusiness addressBusiness;
    private final StateService stateService;
    private final CityService cityService;

    public AddressService(
            StateService stateService,
            CityService cityService,
            AddressBusiness addressBusiness
    ) {
        this.stateService = stateService;
        this.cityService = cityService;
        this.addressBusiness = addressBusiness;
    }

    @Transactional
    public void updateCitiesStates() {
        addressBusiness.updateCitiesStates();
    }

//    @Transactional
//    public Address save(AddressRequestDTO addressRequestDTO, Person person) {
//        City city = cityService.findById(addressRequestDTO.getCityId());
//        Address address = Address.builder()
//                .street(addressRequestDTO.getStreet())
//                .number(addressRequestDTO.getNumber())
//                .complement(addressRequestDTO.getComplement())
//                .cep(addressRequestDTO.getCep())
//                .neighborhood(addressRequestDTO.getNeighborhood())
//                .type(addressRequestDTO.getType())
//                .city(city)
//                .person(person)
//                .build();
//
//        return addressBusiness.save(address);
//    }

    public List<StateResponseDTO> getStates() {
        return stateService.getAll();
    }

    public List<CityResponseDTO> getCitiesByState(UUID stateId) {
        return cityService.getCitiesByState(stateId);
    }
}
