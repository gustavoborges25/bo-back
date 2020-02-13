package com.gusgo.bo.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NaturalPersonRequestDTO {

    @NotBlank
    @Size(min = 1, max = 255)
    private String name;

    @NotBlank
    @CPF
    @Size(min = 11, max = 11)
    private String cpf;

    @Size(max = 30)
    private String rg;

    @NotEmpty
    private List<AddressRequestDTO> addresses;

    @NotEmpty
    private List<PhoneRequestDTO> phones;

    private List<EmailRequestDTO> emails;

}
