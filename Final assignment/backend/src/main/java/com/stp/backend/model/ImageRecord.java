package com.stp.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ImageRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagePath;

    @Column(columnDefinition = "TEXT")
    private String recognizedText;

    private LocalDateTime timestamp;
}
