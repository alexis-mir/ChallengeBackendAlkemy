package com.challenge.java.service;

import com.challenge.java.dto.UserRegisterDTO;
import com.challenge.java.dto.UserRequestDTO;
import com.challenge.java.dto.UserResponseDTO;
import com.challenge.java.mapper.UsuarioMapper;
import com.challenge.java.repository.UsuarioRepository;
import com.challenge.java.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Alexis
 */
@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username).map(u -> {
            GrantedAuthority authority;
            if (u.getRole() == null) {
                authority = new SimpleGrantedAuthority("ROLE_USER");
            } else {
                authority = new SimpleGrantedAuthority("ROLE_" + u.getRole().toString());
            }
            return new User(u.getUsername(), u.getPassword(), List.of(authority));
        }).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public UserResponseDTO singUp(UserRegisterDTO userDTO) {
        UserRequestDTO userRequestDTO = new UserRequestDTO(userDTO.getUsername(), userDTO.getPassword());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        usuarioRepository.save(usuarioMapper.toUsuario(userDTO));
        return login(userRequestDTO);
    }

    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userRequestDTO.getUsername(),
                                    userRequestDTO.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return new UserResponseDTO(jwtUtil.generateToken(userDetails));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getLocalizedMessage());
        }
    }
}
