package com.gusgo.bo.business.registration;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.gusgo.bo.entity.registration.City;
import com.gusgo.bo.entity.support.ibge.IbgeApi;
import com.gusgo.bo.exception.ServiceException;
import com.gusgo.bo.repository.registration.CityRepository;

@Component
public class CityBusiness {

    private final CityRepository cityRepository;
    private final IbgeApi ibgeApi;

    public CityBusiness(IbgeApi ibgeApi, CityRepository cityRepository) {
        this.ibgeApi = ibgeApi;
        this.cityRepository = cityRepository;
    }

    public void updateWithIbgeApi() {
        List<City> cities = ibgeApi.getCities();
        saveCities(cities);
    }

    public List<City> findCitiesByState(UUID stateId) {
        return cityRepository.findCitiesByState(stateId);
    }
    
    public City findById(UUID id) {
        return cityRepository.findById(id).orElseThrow(() -> new ServiceException("BO-2"));
    }

    private void saveCities(List<City> cities) {
        cities.forEach((city -> save(city)));
    }

    private void save(City city) {
        City foundOrNewCity = findByIbgeIdOrNew(city);
        cityRepository.save(foundOrNewCity);
    }

    private City findByIbgeIdOrNew(City city) {
        return cityRepository
                .findByIbgeId(city.getIbgeId())
                .orElse(City
                        .builder()
                        .ibgeId(city.getIbgeId())
                        .name(city.getName())
                        .state(city.getState())
                        .build()
                );
    }
}
