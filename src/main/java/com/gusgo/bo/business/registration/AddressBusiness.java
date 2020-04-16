package com.gusgo.bo.business.registration;

import com.gusgo.bo.service.registration.CityService;
import com.gusgo.bo.service.registration.StateService;
import org.springframework.stereotype.Component;

@Component
public class AddressBusiness {

    private final StateBusiness stateBusiness;
    private final CityBusiness cityBusiness;

    public AddressBusiness(StateBusiness stateBusiness, CityBusiness cityBusiness) {
        this.stateBusiness = stateBusiness;
        this.cityBusiness = cityBusiness;
    }

    public void updateCitiesStates() {
        stateBusiness.updateWithIbgeApi();
        cityBusiness.updateWithIbgeApi();
    }

}
