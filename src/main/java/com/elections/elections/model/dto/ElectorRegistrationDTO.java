package com.elections.elections.model.dto;

import com.elections.elections.model.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ElectorRegistrationDTO {
    @NotBlank(message = "Elector name cannot be blank")
    private String name;
    @NotBlank(message = "Elector surname cannot be blank")
    private String surname;
    @NotBlank(message = "Elector login cannot be blank")
    private String login;
    @NotBlank(message = "Elector password cannot be blank")
    private String password;
    @NotNull(message = "Elector role cannot be blank")
    private Role role;
}
