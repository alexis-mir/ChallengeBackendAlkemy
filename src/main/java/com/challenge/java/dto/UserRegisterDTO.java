package com.challenge.java.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class UserRegisterDTO {
    @NotNull
    private String password;
    @NotNull
    private String username;
    @NotNull
    private String role;
}
