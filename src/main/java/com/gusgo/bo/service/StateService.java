package com.gusgo.bo.service;

import com.gusgo.bo.dto.request.StateRequestDTO;
import com.gusgo.bo.dto.response.StateResponseDTO;
import com.gusgo.bo.entity.State;
import com.gusgo.bo.exception.ServiceException;
import com.gusgo.bo.repository.StateRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StateService {

    private final StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Transactional
    public void save(StateRequestDTO stateRequestDTO) {
        State state = findByIbgeIdOrNew(stateRequestDTO.getIbgeId());
        State updatedState = requestDTOToEntity(stateRequestDTO, state);
        stateRepository.save(updatedState);
    }

    public StateResponseDTO findByIbgeId(int ibgeId) {
        State state = stateRepository.findByIbgeId(ibgeId).orElseThrow(() -> new ServiceException("BO-1"));
        return entityToResponseDTO(state);
    }

    public List<StateResponseDTO> getAll() {
        List<State> states = stateRepository.findAll(Sort.by("name"));
        List<StateResponseDTO> stateResponseDTO = new ArrayList<>();
        states.forEach(state -> stateResponseDTO.add(entityToResponseDTO(state)));
        return stateResponseDTO;
    }

    private State findByIbgeIdOrNew(int ibgeId) {
        return stateRepository.findByIbgeId(ibgeId).orElse(new State());
    }

    private State requestDTOToEntity(StateRequestDTO stateRequestDTO, State state) {
        state.setIbgeId(stateRequestDTO.getIbgeId());
        state.setName(stateRequestDTO.getName());
        state.setAbbreviation(stateRequestDTO.getAbbreviation());

        return state;
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
