package com.gusgo.bo.dto.request.registration;

import lombok.*;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.gusgo.bo.annotation.UUIDValidation;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestDTO {

    @UUIDValidation
    private UUID id;
    
    @NotBlank
    @Email
    @Size(min = 8, max = 50)
    private String email;

}
