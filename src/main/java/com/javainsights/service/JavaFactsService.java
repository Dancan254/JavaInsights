package com.javainsights.service;

import com.javainsights.model.JavaFact;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JavaFactsService {
    private final LLMService llmService;

    @Cacheable(value = "facts", key = "'random'")
    public JavaFact getRandomFact() {
        String fact = llmService.generateRandomFact();
        return new JavaFact("Random", fact);
    }

    @Cacheable(value = "facts", key = "#topic")
    public JavaFact getTopicFact(String topic) {
        String fact = llmService.generateTopicFact(topic);
        return new JavaFact(topic, fact);
    }
}
