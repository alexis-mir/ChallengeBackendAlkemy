package com.JavaProyect.ChallengeBackend.Controllers;

import java.util.Optional;

import com.JavaProyect.ChallengeBackend.Auth.ApplicationUser;
import com.JavaProyect.ChallengeBackend.Auth.ApplicationUserService;
import com.JavaProyect.ChallengeBackend.DTO.ApplicationUserRegisterDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService applicationUserService;

    @GetMapping("/login")
    public String getLoginView (){
        return "login";
    }

    /* @PostMapping(/login)
    public ResponseEntity<ApplicationUser> login(@RequestBody ApplicationUserRegisterDTO user){
        Optional<ApplicationUser> respuesta = applicationUserService.loadUserByUsername(user);
        if (respuesta.isPresent()) {
            return ResponseEntity.ok().body(respuesta.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    } */

    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> registrarUsuario(@RequestBody ApplicationUserRegisterDTO user){
        Optional<ApplicationUser> respuesta = applicationUserService.registrarUsuario(user);
        if (respuesta.isPresent()) {
            return ResponseEntity.ok().body(respuesta.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
}
