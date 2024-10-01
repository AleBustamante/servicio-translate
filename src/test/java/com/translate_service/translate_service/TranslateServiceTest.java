package com.translate_service.translate_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TranslateServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TranslateService translateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTranslate() {
        TranslationResponse fakeResponse = new TranslationResponse();
        fakeResponse.setTranslatedText("Hola");

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(TranslationResponse.class))
        ).thenReturn(ResponseEntity.ok(fakeResponse));

        String result = translateService.translate("Hello", "en", "es");

        assertEquals("Hola", result);

        verify(restTemplate).exchange(
                anyString(),
                eq(HttpMethod.POST),
                any(HttpEntity.class),
                eq(TranslationResponse.class)
        );
    }
}
