package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Long> {
    Optional<Admin> findByEmail(String email);
}
