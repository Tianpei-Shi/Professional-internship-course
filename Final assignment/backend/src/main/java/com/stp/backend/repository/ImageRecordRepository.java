package com.stp.backend.repository;

import com.example.aiapp.model.ImageRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRecordRepository extends JpaRepository<ImageRecord, Long> {
}
