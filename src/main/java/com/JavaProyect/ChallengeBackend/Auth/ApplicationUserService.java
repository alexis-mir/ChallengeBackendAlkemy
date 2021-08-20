package com.JavaProyect.ChallengeBackend.Auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.JavaProyect.ChallengeBackend.DTO.ApplicationUserRegisterDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService{

    @Autowired
	private final PasswordEncoder passwordEncoder;

    @Autowired
    private final ApplicationUserDao applicationUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDao
                .findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("Username %s not found.", username)));
    }

    public Optional<ApplicationUser> registrarUsuario(ApplicationUserRegisterDTO userDTO){
        if (userDTO.getPassword() == null || userDTO.getUsername() == null) {
            return Optional.empty();
        } else {
            List<Roles> authorities = new ArrayList<>();
            authorities.add(Roles.USER);
            ApplicationUser user = new ApplicationUser(
                authorities,
                passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getUsername(),
                true, true, true, true);
            System.out.println(user.toString());
            return Optional.of(applicationUserDao.save(user));
        }
    }

}
