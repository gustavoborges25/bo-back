package com.gusgo.bo.dto.request.registration;

import java.util.UUID;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.gusgo.bo.annotation.UUIDValidation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {

    @UUIDValidation
    private UUID id;
    
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
