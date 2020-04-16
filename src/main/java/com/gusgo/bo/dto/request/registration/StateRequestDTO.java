package com.gusgo.bo.dto.request.registration;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StateRequestDTO {

    @NotNull
    @Digits(integer = 12, fraction = 0)
    private int ibgeId;

    @NotBlank
    @Size(min = 2, max = 2)
    private String abbreviation;

    @NotBlank
    @Size(min = 1, max = 255)
    private String name;

}
