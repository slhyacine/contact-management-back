package com.polyscripts.contactManagement.repositories;

import com.polyscripts.contactManagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository <User, Long> {
    Optional<User> findByUsername(String username);
}
