package com.gusgo.bo.controller.registration;

import com.gusgo.bo.base.BaseController;
import com.gusgo.bo.dto.request.registration.NaturalPersonRequestDTO;
import com.gusgo.bo.dto.response.registration.NaturalPersonResponseDTO;
import com.gusgo.bo.dto.response.registration.ResponseDTO;
import com.gusgo.bo.service.registration.NaturalPersonService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/natural-people")
public class NaturalPersonController extends BaseController {

    private NaturalPersonService naturalPersonService;

    public NaturalPersonController(MessageSource messageSource, NaturalPersonService naturalPersonService) {
        super(messageSource);
        this.naturalPersonService = naturalPersonService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<NaturalPersonResponseDTO>> create(@Valid @RequestBody NaturalPersonRequestDTO naturalPersonRequestDTO) {
        NaturalPersonResponseDTO naturalPersonResponseDTO = naturalPersonService.save(naturalPersonRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO<>(naturalPersonResponseDTO));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<NaturalPersonResponseDTO>>> getAll() {
        List<NaturalPersonResponseDTO> naturalPeopleResponseDTO = naturalPersonService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(naturalPeopleResponseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<NaturalPersonResponseDTO>> getById(@PathVariable("id") UUID id) {
        NaturalPersonResponseDTO naturalPersonResponseDTO = naturalPersonService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(naturalPersonResponseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<NaturalPersonResponseDTO>> update(@PathVariable("id") UUID id, @Valid @RequestBody NaturalPersonRequestDTO naturalPersonRequestDTO) {
        NaturalPersonResponseDTO naturalPersonResponseDTO = naturalPersonService.update(id, naturalPersonRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(naturalPersonResponseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        naturalPersonService.delete(id);
        return ResponseEntity
        		.noContent()
        		.build();
    }
    
}
