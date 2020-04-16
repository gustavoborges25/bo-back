package com.gusgo.bo.service.registration;

import com.gusgo.bo.business.registration.AddressBusiness;
import com.gusgo.bo.dto.request.registration.AddressRequestDTO;
import com.gusgo.bo.dto.request.registration.CityRequestDTO;
import com.gusgo.bo.dto.request.registration.StateRequestDTO;
import com.gusgo.bo.dto.response.registration.CityResponseDTO;
import com.gusgo.bo.dto.response.registration.StateResponseDTO;
import com.gusgo.bo.entity.registration.*;
import com.gusgo.bo.entity.support.ibge.IbgeCity;
import com.gusgo.bo.entity.support.ibge.IbgeState;
import com.gusgo.bo.repository.registration.AddressRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    private final String URL_STATES = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    private final String URL_CITIES = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios";

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
