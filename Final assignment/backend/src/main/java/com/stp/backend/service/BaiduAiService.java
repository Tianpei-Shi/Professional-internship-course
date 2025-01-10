package com.stp.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class BaiduAiService {
    @Value("${baidu.api-key}")
    private String apiKey;

    @Value("${baidu.secret-key}")
    private String secretKey;

    private String accessToken;

    // 获取百度 API AccessToken
    public String getAccessToken() {
        if (accessToken == null) {
            String url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials" +
                    "&client_id=" + apiKey +
                    "&client_secret=" + secretKey;

            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> response = restTemplate.postForObject(url, null, Map.class);
            accessToken = response.get("access_token");
        }
        return accessToken;
    }

    // 调用图片识别接口
    public Map<String, Object> recognizeImage(String base64Image) {
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/object_detect?access_token=" + getAccessToken();
        Map<String, String> request = new HashMap<>();
        request.put("image", base64Image);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, request, Map.class);
    }

    // 调用 AI 对话接口
    public Map<String, Object> chatWithAi(String userInput) {
        String url = "https://aip.baidubce.com/rpc/2.0/unit/service/v3/chat?access_token=" + getAccessToken();
        Map<String, Object> request = new HashMap<>();
        request.put("query", userInput);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, request, Map.class);
    }
}
