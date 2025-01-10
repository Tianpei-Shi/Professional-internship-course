package com.stp.backend.controller;
import java.util.Map;
import com.stp.backend.service.BaiduAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private BaiduAiService baiduAiService;

    @PostMapping
    public Map<String, Object> chatWithAi(@RequestBody Map<String, String> request) {
        String userInput = request.get("userInput");
        return baiduAiService.chatWithAi(userInput);
    }
}
