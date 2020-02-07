package com.gusgo.bo.controller;

import com.gusgo.bo.base.BaseController;
import com.gusgo.bo.dto.response.CityResponseDTO;
import com.gusgo.bo.dto.response.ResponseDTO;
import com.gusgo.bo.dto.response.StateResponseDTO;
import com.gusgo.bo.service.AddressService;
import com.gusgo.bo.service.CityService;
import com.gusgo.bo.service.StateService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/address")
public class AddressController extends BaseController {

    private final AddressService addressService;
    private final CityService cityService;
    private final StateService stateService;

    public AddressController(MessageSource messageSource, AddressService addressService, CityService cityService, StateService stateService) {
        super(messageSource);
        this.addressService = addressService;
        this.cityService = cityService;
        this.stateService = stateService;
    }

    @PostMapping("/update-cities-states")
    public ResponseEntity<Void> updateCitiesStates() {
        addressService.updateStates();
        addressService.updateCities();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/states")
    public ResponseEntity<ResponseDTO<List<StateResponseDTO>>> getStates() {
        List<StateResponseDTO> stateResponseDTO = stateService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(stateResponseDTO));
    }

    @GetMapping("/states/{stateId}/cities")
    public ResponseEntity<ResponseDTO<List<CityResponseDTO>>> getCities(@PathVariable("stateId") UUID stateId) {
        List<CityResponseDTO> citiesResponseDTO = cityService.getCitiesByState(stateId);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(citiesResponseDTO));
    }
}
