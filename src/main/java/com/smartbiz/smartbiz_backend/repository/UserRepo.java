package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
