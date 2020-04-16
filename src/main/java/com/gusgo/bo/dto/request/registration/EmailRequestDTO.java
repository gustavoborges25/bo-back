package com.gusgo.bo.dto.request.registration;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestDTO {

    @NotBlank
    @Email
    @Size(min = 8, max = 50)
    private String email;

}
