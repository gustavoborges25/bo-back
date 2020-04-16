package com.gusgo.bo.service.registration;

import com.gusgo.bo.business.registration.CityBusiness;
import com.gusgo.bo.dto.request.registration.CityRequestDTO;
import com.gusgo.bo.dto.response.registration.CityResponseDTO;
import com.gusgo.bo.entity.registration.City;
import com.gusgo.bo.entity.registration.State;
import com.gusgo.bo.exception.ServiceException;
import com.gusgo.bo.repository.registration.CityRepository;
import com.gusgo.bo.repository.registration.StateRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CityService {

    private final CityBusiness cityBusiness;

    public CityService(CityBusiness cityBusiness) {
        this.cityBusiness = cityBusiness;
    }

    public List<CityResponseDTO> getCitiesByState(UUID stateId) {
        List<City> cities = cityBusiness.findCitiesByState(stateId);
        List<CityResponseDTO> citiesResponseDTO = new ArrayList<>();
        cities.forEach(city -> citiesResponseDTO.add(entityToResponseDTO(city)));
        return citiesResponseDTO;
    }

    private CityResponseDTO entityToResponseDTO(City city) {
        return CityResponseDTO.builder()
                .id(city.getId())
                .ibgeId(city.getIbgeId())
                .name(city.getName())
                .stateId(city.getState().getId())
                .build();
    }

}
