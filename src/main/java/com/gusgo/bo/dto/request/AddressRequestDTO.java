package com.gusgo.bo.dto.request;

import com.gusgo.bo.annotation.UUIDValidation;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {

    @NotBlank
    @Size(min = 1, max = 255)
    private String street;

    @NotBlank
    @Size(min = 1, max = 20)
    private String number;

    private String complement;

    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;

    @NotBlank
    @Size(min = 1, max = 255)
    private String neighborhood;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String type;

    @NotBlank
    @UUIDValidation
    private UUID cityId;

}
