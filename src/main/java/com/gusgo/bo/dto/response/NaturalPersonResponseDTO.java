package com.gusgo.bo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NaturalPersonResponseDTO {

    private UUID id;
    private String name;
    private String cpf;
    private String rg;
    private List<AddressResponseDTO> addresses;
    private List<PhoneResponseDTO> phones;
    private List<EmailResponseDTO> emails;

}
