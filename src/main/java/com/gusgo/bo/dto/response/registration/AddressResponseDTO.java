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
public class AddressResponseDTO {

    private UUID id;
    private String street;
    private String number;
    private String complement;
    private String cep;
    private String neighborhood;
    private String type;
    private UUID cityId;

}
