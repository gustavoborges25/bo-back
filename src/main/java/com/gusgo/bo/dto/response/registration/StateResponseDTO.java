package com.gusgo.bo.dto.response.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StateResponseDTO {
    private UUID id;
    private int ibgeId;
    private String abbreviation;
    private String name;
}
