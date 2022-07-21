package com.example.internallearningtesttask.externalhrsource.repository;

import com.example.internallearningtesttask.externalhrsource.ExternalHrSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalHrSourceRepository extends JpaRepository<ExternalHrSource, Long> {
    boolean existsByEmail(String email);
}
