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
        return getUser(userId).getProfilePicture();
    }

    @Transactional
    public String uploadUserPicture(UUID userId, MultipartFile file) throws IOException {
        User user = getUser(userId);
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

        return url;
    }

    public UserProfileResponseDto getUserProfile(UUID id) {
        User user = getUser(id);

        String picture = user.getProfilePicture();
        String username = user.getUsername();

        return UserProfileResponseDto.builder()
                .picture(picture)
                .username(username)
                .build();
    }

    @Transactional
    public User updateUsername(UsernameRequestDto data) {
        User user = getUser(data.userId());

        user.setName(data.username());
        repository.save(user);

        return user;
    }

    @Transactional
    public User updateEmail(EmailRequestDto data) {
        User user = getUser(data.userId());

        user.setEmail(data.email());

        repository.save(user);

        return user;
    }

    @Transactional
    public User updateWeeklyGoal(UUID userId, Integer weeklyGoal) {
        User user = getUser(userId);

        user.setWeeklyGoal(weeklyGoal);

        return repository.save(user);
    }


    private User getUser(UUID id) {
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
}
