package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.AIRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AIRequestRepo extends JpaRepository<AIRequest,Long> {
    List<AIRequest> findByBusinessId(Long businessId);
    List<AIRequest> findByBusinessIdOrderByCreatedAtDesc(Long businessId);
    long countByCreatedAtAfter(LocalDateTime since);
}
