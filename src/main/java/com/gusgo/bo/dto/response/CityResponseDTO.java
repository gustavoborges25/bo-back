package com.gusgo.bo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityResponseDTO {
    private UUID id;
    private int ibgeId;
    private String name;
    private UUID stateId;

}
