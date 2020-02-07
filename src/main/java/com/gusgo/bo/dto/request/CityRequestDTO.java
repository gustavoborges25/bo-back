package com.gusgo.bo.dto.request;

import com.gusgo.bo.annotation.UUIDValidation;
import com.gusgo.bo.entity.State;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CityRequestDTO {

    @NotNull
    @Digits(integer = 12, fraction = 0)
    private int ibgeId;

    @NotBlank
    @Size(min = 1, max = 255)
    private String name;

    @NotBlank
    @UUIDValidation
    private UUID stateId;

}
