package com.gusgo.bo.controller.registration;

import com.gusgo.bo.base.BaseController;
import com.gusgo.bo.dto.response.registration.CityResponseDTO;
import com.gusgo.bo.dto.response.registration.ResponseDTO;
import com.gusgo.bo.dto.response.registration.StateResponseDTO;
import com.gusgo.bo.service.registration.AddressService;
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

    public AddressController(
            MessageSource messageSource,
            AddressService addressService
    ) {
        super(messageSource);
        this.addressService = addressService;
    }

    @PostMapping("/update-cities-states")
    public ResponseEntity<Void> updateCitiesStates() {
        addressService.updateCitiesStates();
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/states")
    public ResponseEntity<ResponseDTO<List<StateResponseDTO>>> getStates() {
        List<StateResponseDTO> stateResponseDTO = addressService.getStates();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO<>(stateResponseDTO));
    }

    @GetMapping("/states/{stateId}/cities")
    public ResponseEntity<ResponseDTO<List<CityResponseDTO>>> getCities(@PathVariable("stateId") UUID stateId) {
        List<CityResponseDTO> citiesResponseDTO = addressService.getCitiesByState(stateId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO<>(citiesResponseDTO));
    }
}
