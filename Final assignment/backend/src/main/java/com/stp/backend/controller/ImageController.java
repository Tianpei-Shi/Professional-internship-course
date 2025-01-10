package com.stp.backend.controller;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import com.stp.backend.service.BaiduAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private BaiduAiService baiduAiService;

    @PostMapping("/recognize")
    public Map<String, Object> recognizeImage(@RequestParam MultipartFile image) throws IOException {
        // 将图片转为 Base64
        String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
        return baiduAiService.recognizeImage(base64Image);
    }
}
