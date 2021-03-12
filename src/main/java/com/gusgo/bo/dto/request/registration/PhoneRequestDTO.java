package com.gusgo.bo.dto.request.registration;

import lombok.*;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.gusgo.bo.annotation.UUIDValidation;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequestDTO {

    @UUIDValidation
    private UUID id;
    
    @NotBlank
    @Size(min = 8, max = 30)
    private String number;

}
