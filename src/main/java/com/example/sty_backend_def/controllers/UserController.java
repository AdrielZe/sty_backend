package com.example.sty_backend_def.controllers;

import com.example.sty_backend_def.domains.models.user.EmailRequestDto;
import com.example.sty_backend_def.domains.models.user.PictureResponseDto;
import com.example.sty_backend_def.domains.models.user.UsernameRequestDto;
import com.example.sty_backend_def.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // get user picture
    @GetMapping("/{id}/picture")
    public ResponseEntity<PictureResponseDto> getUserPicture(@PathVariable("id") UUID id) {
        String url = service.getUserPicture(id);
        return ResponseEntity.ok(new PictureResponseDto(url));
    }

    // post user picture
    @PostMapping("{id}/picture")
    public ResponseEntity<String> postUserPicture(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = service.uploadUserPicture(id, file);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error ao processar imagem");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getUser(id).orElseThrow(() -> new RuntimeException("User doesn't exist")));
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity getUserProfile(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getUserProfile(id));
    }

    @PostMapping("/{id}/username")
    public ResponseEntity updateUsername(@Valid @RequestBody UsernameRequestDto data) {
        return ResponseEntity.ok(service.updateUsername(data));
    }

    @PostMapping("/{id}/email")
    public ResponseEntity updateEmail(@Valid @RequestBody EmailRequestDto data) {
        return ResponseEntity.ok(service.updateEmail(data));
    }

}
