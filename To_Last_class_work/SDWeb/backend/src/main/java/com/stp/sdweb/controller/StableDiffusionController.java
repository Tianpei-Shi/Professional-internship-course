package com.stp.sdweb.controller;

import com.stp.sdweb.entity.ImageRecord;
import com.stp.sdweb.service.ImageRecordService;
import com.stp.sdweb.service.StableDiffusionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stablediffusion")
@CrossOrigin(origins = "*") // 允许跨域
public class StableDiffusionController {

    @Autowired
    private StableDiffusionService diffusionService;

    @Autowired
    private ImageRecordService recordService;

    @PostMapping("/generate")
    public ImageRecord generateImage(@RequestBody ImageRecord record) {
        // 调用Stable Diffusion API生成图片
        String base64Image = diffusionService.generateImage(
            record.getPrompt(),
            record.getSteps(),
            record.getCfgScale(),
            record.getWidth(),
            record.getHeight()
        );

        // 保存记录到数据库
        record.setBase64Image(base64Image);
        record.setCreatedAt(LocalDateTime.now());
        return recordService.saveImageRecord(record);
    }

    @GetMapping("/history")
    public List<ImageRecord> getHistory() {
        return recordService.getAllRecords();
    }
}
