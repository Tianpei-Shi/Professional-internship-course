package com.stp.sdweb.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StableDiffusionService {

    private static final String API_URL = "http://127.0.0.1:7860/sdapi/v1/txt2img";

    public String generateImage(String prompt, int steps, int cfgScale, int width, int height) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = String.format(
            "{\"prompt\":\"%s\", \"steps\":%d, \"cfg_scale\":%d, \"width\":%d, \"height\":%d}",
            prompt, steps, cfgScale, width, height
        );

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
            API_URL,
            HttpMethod.POST,
            requestEntity,
            String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return extractBase64Image(response.getBody());
        }

        throw new RuntimeException("Failed to generate image");
    }

    private String extractBase64Image(String responseBody) {
        return responseBody.split("\"images\":\\[\"")[1].split("\"\\]")[0];
    }
}
