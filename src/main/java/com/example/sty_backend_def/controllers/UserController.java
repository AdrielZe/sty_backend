package com.example.sty_backend_def.controllers;

import com.example.sty_backend_def.domains.models.user.*;
import com.example.sty_backend_def.services.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    @GetMapping("/{id}/picture")
    public ResponseEntity<PictureResponseDto> getUserPicture(@PathVariable("id") UUID id) {
        String url = service.getUserPicture(id);
        return ResponseEntity.ok(new PictureResponseDto(url));
    }

    @PostMapping("/{id}/picture")
    public ResponseEntity<PictureResponseDto> postUserPicture(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        try {
            PictureResponseDto imageUrl = service.uploadUserPicture(id, file);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(new PictureResponseDto("failure"));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponseDto> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(service.getUserProfile(id));
    }

    @PostMapping("/{id}/username")
    public ResponseEntity<UserProfileResponseDto> updateUsername(@Valid @RequestBody UsernameRequestDto data) {
        return ResponseEntity.ok(service.updateUsername(data));
    }

    @PostMapping("/{id}/email")
    public ResponseEntity<UserProfileResponseDto> updateEmail(@Valid @RequestBody EmailRequestDto data) {
        return ResponseEntity.ok(service.updateEmail(data));
    }

    @PostMapping("/{id}/weeklyGoal")
    public ResponseEntity<UserProfileResponseDto> updateWeeklyGoal(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UserWeeklyGoalRequestDto data
    ) {
        return ResponseEntity.ok(service.updateWeeklyGoal(id, data));
    }
}
