package com.gusgo.bo.service;

import com.gusgo.bo.dto.request.AddressRequestDTO;
import com.gusgo.bo.dto.request.CityRequestDTO;
import com.gusgo.bo.dto.request.StateRequestDTO;
import com.gusgo.bo.entity.*;
import com.gusgo.bo.repository.AddressRepository;
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

    private final AddressRepository addressRepository;
    private final StateService stateService;
    private final CityService cityService;

    public AddressService(StateService stateService, CityService cityService, AddressRepository addressRepository) {
        this.stateService = stateService;
        this.cityService = cityService;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public void updateStates() {
        List<IbgeState> states = getStatesInIbgeApi();
        saveStates(states);
    }
    @Transactional
    public void updateCities() {
        List<IbgeCity> cities = getCitiesInIbgeApi();
        saveCities(cities);
    }

    @Transactional
    public Address save(AddressRequestDTO addressRequestDTO, Person person) {
        City city = cityService.findById(addressRequestDTO.getCityId());
        Address address = Address.builder()
                .street(addressRequestDTO.getStreet())
                .number(addressRequestDTO.getNumber())
                .complement(addressRequestDTO.getComplement())
                .cep(addressRequestDTO.getCep())
                .neighborhood(addressRequestDTO.getNeighborhood())
                .type(addressRequestDTO.getType())
                .city(city)
                .person(person)
                .build();

        return addressRepository.save(address);
    }

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return restTemplate;
    }

    private List<IbgeState> getListIbgeStates(RestTemplate restTemplate) {
        ResponseEntity<List<IbgeState>> response = restTemplate.exchange(
                URL_STATES,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<IbgeState>>() {});
        return response.getBody();
    }

    private List<IbgeCity> getListIbgeCities(RestTemplate restTemplate) {
        ResponseEntity<List<IbgeCity>> response = restTemplate.exchange(
                URL_CITIES,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<IbgeCity>>() {});
        return response.getBody();
    }


    private void saveStates(List<IbgeState> states) {
        states.forEach((state -> stateService.save(new StateRequestDTO(state.getId(), state.getSigla(),state.getNome()))));
    }

    private void saveCities(List<IbgeCity> cities) {
        cities.forEach((city -> {
            UUID id = stateService.findByIbgeId(city.getStateIbgeId()).getId();
            cityService.save(new CityRequestDTO(city.getId(), city.getNome(), id));
        }));
    }

    private List<IbgeState> getStatesInIbgeApi() {
        RestTemplate restTemplate = getRestTemplate();
        return getListIbgeStates(restTemplate);
    }

    private List<IbgeCity> getCitiesInIbgeApi() {
        RestTemplate restTemplate = getRestTemplate();
        return getListIbgeCities(restTemplate);
    }


}
