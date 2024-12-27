package com.stp.sdweb.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class ImageRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prompt; // 提示词
    private int steps; // 生成步数
    private int cfgScale; // CFG Scale
    private int width; // 图片宽度
    private int height; // 图片高度

    @Column(columnDefinition = "LONGTEXT") // 存储 Base64 图片
    private String base64Image;

    private LocalDateTime createdAt; // 生成时间
}
