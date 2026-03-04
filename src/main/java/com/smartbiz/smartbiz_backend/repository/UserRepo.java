package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByBusinessBusinessId(Long businessId);
}
