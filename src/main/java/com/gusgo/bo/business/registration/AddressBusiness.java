package com.gusgo.bo.business.registration;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.gusgo.bo.entity.registration.Address;
import com.gusgo.bo.exception.ServiceException;
import com.gusgo.bo.repository.registration.AddressRepository;

@Component
public class AddressBusiness {

    private final AddressRepository addressRepository;
    private final StateBusiness stateBusiness;
    private final CityBusiness cityBusiness;

    public AddressBusiness(
    		AddressRepository addressRepository, 
    		StateBusiness stateBusiness, 
    		CityBusiness cityBusiness) {
        this.addressRepository = addressRepository;
        this.stateBusiness = stateBusiness;
        this.cityBusiness = cityBusiness;
    }

    public void updateCitiesStates() {
        stateBusiness.updateWithIbgeApi();
        cityBusiness.updateWithIbgeApi();
    }
    
    public Address findById(UUID id) {
        return addressRepository.findById(id).orElseThrow(() -> new ServiceException("BO-4"));
    }

}
