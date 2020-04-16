package com.gusgo.bo.business.registration;

import com.gusgo.bo.dto.request.registration.StateRequestDTO;
import com.gusgo.bo.dto.response.registration.StateResponseDTO;
import com.gusgo.bo.entity.registration.State;
import com.gusgo.bo.entity.support.ibge.IbgeApi;
import com.gusgo.bo.entity.support.ibge.IbgeState;
import com.gusgo.bo.exception.ServiceException;
import com.gusgo.bo.repository.registration.StateRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateBusiness {

    private final StateRepository stateRepository;
    private final IbgeApi ibgeApi;

    public StateBusiness(
            StateRepository stateRepository,
            IbgeApi ibgeApi
    ) {
        this.stateRepository = stateRepository;
        this.ibgeApi = ibgeApi;
    }

    public void updateWithIbgeApi() {
        List<State> states = ibgeApi.getStates();
        saveStates(states);
    }

    public List<State> getAll(){
        return stateRepository.findAll(Sort.by("name"));
    }

    public State findByIbgeId(int ibgeId) {
        return stateRepository.findByIbgeId(ibgeId).orElseThrow(() -> new ServiceException("BO-1"));
    }

    private void saveStates(List<State> states) {
        states.forEach((state -> save(state)));
    }

    private void save(State state) {
        State foundOrNewState = findByIbgeIdOrNew(state);
        stateRepository.save(foundOrNewState);
    }

    private State findByIbgeIdOrNew(State state) {
        return stateRepository
                .findByIbgeId(state.getIbgeId())
                .orElse(State
                        .builder()
                        .ibgeId(state.getIbgeId())
                        .abbreviation(state.getAbbreviation())
                        .name(state.getName())
                        .build()
                );
    }



}
