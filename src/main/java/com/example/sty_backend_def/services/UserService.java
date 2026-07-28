package com.example.sty_backend_def.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.sty_backend_def.domains.models.user.*;
import com.example.sty_backend_def.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    private final UserRepository repository;
    private final Cloudinary cloudinary;

    public UserService(UserRepository repository, Cloudinary cloudinary) {
        this.repository = repository;
        this.cloudinary = cloudinary;
    }

    public String getUserPicture(UUID userId) {
        return searchUser(userId).getProfilePicture();
    }

    @Transactional
    public PictureResponseDto uploadUserPicture(UUID userId, MultipartFile file) throws IOException {
        User user = searchUser(userId);
        validateImageFile(file);
        deleteImageIfExisting(user);

        var params = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );

        var uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
        String url = uploadResult.get("secure_url").toString();
        String publicId = uploadResult.get("public_id").toString();

        user.setProfilePicture(url);
        user.setProfilePicturePublicId(publicId);

        repository.save(user);

        return new PictureResponseDto(url);
    }

    public UserProfileResponseDto getUserProfile(UUID id) {
        User user = searchUser(id);

        return toProfileDto(user);
    }

    @Transactional
    public UserProfileResponseDto updateUsername(UsernameRequestDto data) {
        User user = searchUser(data.userId());

        user.setName(data.username());
        repository.save(user);

        return toProfileDto(user);
    }

    @Transactional
    public UserProfileResponseDto updateEmail(EmailRequestDto data) {
        User user = searchUser(data.userId());

        user.setEmail(data.email());

        repository.save(user);

        return toProfileDto(user);
    }

    @Transactional
    public UserProfileResponseDto updateWeeklyGoal(UUID userId, UserWeeklyGoalRequestDto data) {
        User user = searchUser(userId);

        user.setWeeklyGoal(data.weeklyGoal());

        repository.save(user);

        return toProfileDto(user);
    }


    private User searchUser(UUID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    private void deleteImageIfExisting(User user) throws IOException {
        String oldPublicId = user.getProfilePicturePublicId();
        if (oldPublicId != null && !oldPublicId.isEmpty()) {
            cloudinary.uploader().destroy(oldPublicId, ObjectUtils.emptyMap());
        }

    }

    private void validateImageFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Image file cannot be empty");
        }

        String contentType = file.getContentType();

        if (contentType == null || !contentType.startsWith("image/")){
            throw new IllegalArgumentException("File must be an image.");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("File exceeds the max size of 5MB.");
        }
    }

    private UserProfileResponseDto toProfileDto(User user) {
        return UserProfileResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .weeklyGoal(user.getWeeklyGoal())
                .picture(user.getProfilePicture())
                .build();
    }
}
