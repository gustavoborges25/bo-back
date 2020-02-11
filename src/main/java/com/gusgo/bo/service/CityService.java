package com.gusgo.bo.service;

import com.gusgo.bo.dto.request.CityRequestDTO;
import com.gusgo.bo.dto.response.CityResponseDTO;
import com.gusgo.bo.entity.City;
import com.gusgo.bo.entity.State;
import com.gusgo.bo.exception.ServiceException;
import com.gusgo.bo.repository.CityRepository;
import com.gusgo.bo.repository.StateRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    public CityService(CityRepository cityRepository, StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    @Transactional
    public CityResponseDTO save(CityRequestDTO cityRequestDTO) {
        // validaçao
        City city = findByIbgeIdOrNew(cityRequestDTO.getIbgeId());
        City updatedCity = requestDTOToEntity(cityRequestDTO, city);
        cityRepository.save(updatedCity);

        return entityToResponseDTO(updatedCity);
    }

    public List<CityResponseDTO> getCitiesByState(UUID stateId) {
        List<City> cities = cityRepository.findCitiesByState(stateId);
        List<CityResponseDTO> citiesResponseDTO = new ArrayList<>();
        cities.forEach(city -> citiesResponseDTO.add(entityToResponseDTO(city)));
        return citiesResponseDTO;
    }

    private City findByIbgeIdOrNew(int ibgeId) {
        return cityRepository.findByIbgeId(ibgeId).orElse(new City());
    }

    private City requestDTOToEntity(CityRequestDTO cityRequestDTO, City city) {
        city.setIbgeId(cityRequestDTO.getIbgeId());
        city.setName(cityRequestDTO.getName());
        State state = stateRepository.findById(cityRequestDTO.getStateId()).orElseThrow(() -> new ServiceException("BO-1"));
        city.setState(state);

        return city;
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
