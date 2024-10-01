package com.translate_service.translate_service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.Map;

@Service
public class TranslateService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String TRANSLATE_API_URL = "https://libretranslate.com/translate";


    public String translate(String text, String sourceLang, String targetLang) {
        Map<String, String> body = new HashMap<>();
        body.put("q", text);
        body.put("source", sourceLang);
        body.put("target", targetLang);
        body.put("format", "text");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<TranslationResponse> response = restTemplate.exchange(
                TRANSLATE_API_URL,
                HttpMethod.POST,
                entity,
                TranslationResponse.class
        );

        return response.getBody().getTranslatedText();
    }
}
