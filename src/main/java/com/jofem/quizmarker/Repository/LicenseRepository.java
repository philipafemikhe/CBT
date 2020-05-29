package com.jofem.quizmarker.Repository;

import com.jofem.quizmarker.model.License;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicenseRepository extends JpaRepository<License, Long> {
    List<License> findByStatus(String pending);

    License findByUserId(Long userId);
    // public License findById(Long id);
}
