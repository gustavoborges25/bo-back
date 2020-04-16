package com.gusgo.bo.entity.support.ibge;

import com.gusgo.bo.business.registration.StateBusiness;
import com.gusgo.bo.entity.registration.City;
import com.gusgo.bo.entity.registration.State;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class IbgeApi {

    private final String URL_STATES = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    private final String URL_CITIES = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios";
    private final StateBusiness stateBusiness;

    private RestTemplate restTemplate;

    public IbgeApi(@Lazy StateBusiness stateBusiness) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.stateBusiness = stateBusiness;
    }

    public List<State> getStates() {
        ResponseEntity<List<IbgeState>> response = restTemplate.exchange(
                URL_STATES,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<IbgeState>>() {});

        List<State> states = ibgeStatesToStates(response.getBody());

        return states;
    }

    public List<City> getCities() {
        ResponseEntity<List<IbgeCity>> response = restTemplate.exchange(
                URL_CITIES,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<IbgeCity>>() {});

        List<City> cities = ibgeCitiesToCities(response.getBody());

        return cities;
    }

    private List<State> ibgeStatesToStates(List<IbgeState> ibgeStates) {
        List<State> states = new ArrayList<>();
        ibgeStates.forEach(ibgeState -> states.add(State
                .builder()
                .ibgeId(ibgeState.getId())
                .name(ibgeState.getNome())
                .abbreviation(ibgeState.getSigla())
                .build()
        ));

        return states;
    }

    private List<City> ibgeCitiesToCities(List<IbgeCity> ibgeCities) {
        List<City> cities = new ArrayList<>();
        ibgeCities.forEach(ibgeCity -> {
            State state = stateBusiness.findByIbgeId(ibgeCity.getStateIbgeId());
            cities.add(City
                .builder()
                .ibgeId(ibgeCity.getId())
                .name(ibgeCity.getNome())
                .state(state)
                .build());
                }
        );

        return cities;
    }


}
