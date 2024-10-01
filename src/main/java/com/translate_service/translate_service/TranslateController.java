package com.translate_service.translate_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/translate")
public class TranslateController {

    @Autowired
    private TranslateService translateService;

    @GetMapping
    public String translate(@RequestParam String text,
                            @RequestParam String sourceLang,
                            @RequestParam String targetLang) {
        return translateService.translate(text, sourceLang, targetLang);
    }
}
