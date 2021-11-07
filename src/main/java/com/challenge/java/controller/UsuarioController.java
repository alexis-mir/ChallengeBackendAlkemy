package com.challenge.java.controller;

import com.challenge.java.dto.UserRegisterDTO;

import com.challenge.java.dto.UserRequestDTO;
import com.challenge.java.dto.UserResponseDTO;
import com.challenge.java.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(usuarioService.login(userRequestDTO), HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterDTO userRegisterDTO){
        return new ResponseEntity<>(usuarioService.singUp(userRegisterDTO), HttpStatus.CREATED);
    }
    
}
