package org.example.calendarmavenjavaspring.repository;

import org.example.calendarmavenjavaspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByNickname(String username);
}
