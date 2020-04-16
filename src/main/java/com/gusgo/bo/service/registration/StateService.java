package com.gusgo.bo.service.registration;

import com.gusgo.bo.business.registration.StateBusiness;
import com.gusgo.bo.dto.request.registration.StateRequestDTO;
import com.gusgo.bo.dto.response.registration.StateResponseDTO;
import com.gusgo.bo.entity.registration.State;
import com.gusgo.bo.exception.ServiceException;
import com.gusgo.bo.repository.registration.StateRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StateService {

    private final StateBusiness stateBusiness;

    public StateService(StateBusiness stateBusiness) {
        this.stateBusiness = stateBusiness;
    }

    public List<StateResponseDTO> getAll() {
        List<State> states = stateBusiness.getAll();
        List<StateResponseDTO> stateResponseDTO = new ArrayList<>();
        states.forEach(state -> stateResponseDTO.add(entityToResponseDTO(state)));
        return stateResponseDTO;
    }

    private StateResponseDTO entityToResponseDTO(State state) {
        return StateResponseDTO.builder()
                .id(state.getId())
                .ibgeId(state.getIbgeId())
                .name(state.getName())
                .abbreviation(state.getAbbreviation())
                .build();
    }

}
