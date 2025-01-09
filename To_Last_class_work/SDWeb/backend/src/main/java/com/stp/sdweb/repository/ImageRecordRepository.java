package com.stp.sdweb.repository;

import com.stp.sdweb.entity.ImageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRecordRepository extends JpaRepository<ImageRecord, Long> {
}
