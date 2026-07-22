package com.example.sty_backend_def.services;

import com.example.sty_backend_def.domains.models.auth.AuthRequestDTO;
import com.example.sty_backend_def.domains.models.auth.AuthResponseDTO;
import com.example.sty_backend_def.domains.models.user.RegisterRequestDto;
import com.example.sty_backend_def.domains.models.user.User;
import com.example.sty_backend_def.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository repository, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
    }


    public AuthResponseDTO registerUser(RegisterRequestDto data) {
        if (repository.findByName(data.login()) != null) throw new RuntimeException("User already registered");

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User user = repository.save(User.builder()
                .name(data.login())
                .password(encryptedPassword)
                .userRole(data.role())
                .build()
        );

        return new AuthResponseDTO(
                user.getId(),
                user.getName()
        );
    }

    public AuthResponseDTO login(AuthRequestDTO data) {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var user = (User) auth.getPrincipal();

            return new AuthResponseDTO(user.getId(), user.getName());
    }

}
