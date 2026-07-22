package com.example.sty_backend_def.controllers;

import com.example.sty_backend_def.domains.models.auth.AuthRequestDTO;
import com.example.sty_backend_def.domains.models.auth.AuthResponseDTO;
import com.example.sty_backend_def.domains.models.user.RegisterRequestDto;
import com.example.sty_backend_def.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO data) {
        return ResponseEntity.ok(service.login(data));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registerUser(@Valid @RequestBody RegisterRequestDto data) {
        return ResponseEntity.ok(service.registerUser(data));
    }

}
