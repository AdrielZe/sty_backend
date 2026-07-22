package com.example.sty_backend_def.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.sty_backend_def.domains.models.user.*;
import com.example.sty_backend_def.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    private final Cloudinary cloudinary;

    public UserService(UserRepository repository, Cloudinary cloudinary) {
        this.repository = repository;
        this.cloudinary = cloudinary;
    }

    public String getUserPicture(UUID userId) {
        return repository.findById(userId).map(User::getProfilePicture).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public String uploadUserPicture(UUID userId, MultipartFile file) throws IOException {
        var params = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );

        var uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
        String url = uploadResult.get("secure_url").toString();

        User user = repository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        user.setProfilePicture(url);

        repository.save(user);

        return url;
    }

    public UserProfileResponseDto getUserProfile(UUID id) {
        User user = searchUser(id);

        String picture = user.getProfilePicture();
        String username = user.getUsername();

        return UserProfileResponseDto.builder()
                .picture(picture)
                .username(username)
                .build();
    }

    public Optional<User> getUser(UUID id) {
        Optional<User> user = repository.findById(id);

        return user;
    }

    public String updateUsername(UsernameRequestDto data) {
        User user = searchUser(data.userId());

        user.setName(data.username());
        repository.save(user);

        return data.username();
    }

    public String updateEmail(EmailRequestDto data) {
        User user = searchUser(data.userId());

        user.setEmail(data.email());

        repository.save(user);

        return data.email();
    }

    private User searchUser(UUID userId) {
        return repository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
