package com.gusgo.bo.controller.registration;

import java.util.List;
import java.util.UUID;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusgo.bo.base.BaseController;
import com.gusgo.bo.dto.response.registration.CityResponseDTO;
import com.gusgo.bo.dto.response.registration.ResponseDTO;
import com.gusgo.bo.dto.response.registration.StateResponseDTO;
import com.gusgo.bo.service.registration.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {

    private final AddressService addressService;

    public AddressController(
            MessageSource messageSource,
            AddressService addressService
    ) {
        super(messageSource);
        this.addressService = addressService;
    }

    @PostMapping("/v1/update-cities-states")
    public ResponseEntity<Void> updateCitiesStates() {
        addressService.updateCitiesStates();
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/v1/states")
    public ResponseEntity<ResponseDTO<List<StateResponseDTO>>> getStates() {
        List<StateResponseDTO> stateResponseDTO = addressService.getStates();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO<>(stateResponseDTO));
    }

    @GetMapping("/v1/states/{stateId}/cities")
    public ResponseEntity<ResponseDTO<List<CityResponseDTO>>> getCities(@PathVariable("stateId") UUID stateId) {
        List<CityResponseDTO> citiesResponseDTO = addressService.getCitiesByState(stateId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO<>(citiesResponseDTO));
    }
}
