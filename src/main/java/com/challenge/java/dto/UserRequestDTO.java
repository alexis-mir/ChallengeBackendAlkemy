package com.challenge.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Alexis
 */
@Data
@AllArgsConstructor
public class UserRequestDTO {
    private String username;
    private String password;
}
