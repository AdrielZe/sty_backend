package com.example.sty_backend_def.repositories;

import com.example.sty_backend_def.domains.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  //  UserDetails findByName(String name);
    User findByName(String name);
    User findByEmail(String email);

}
