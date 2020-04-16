package com.gusgo.bo.dto.request.registration;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequestDTO {

    @NotBlank
    @Size(min = 8, max = 30)
    private String number;

}
