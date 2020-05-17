package com.eck_analytics.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 64)
    private String username;

    @NotBlank
    @Size(max = 128)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 128)
    private String password;

    private String role;
}
