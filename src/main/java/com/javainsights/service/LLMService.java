package com.javainsights.service;

import dev.langchain4j.model.azure.AzureOpenAiChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.PromptTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class LLMService {
    private final ChatLanguageModel model;
    private final PromptTemplate topicFactTemplate;
    private final String[] javaTopics = {
        "language features and syntax",
        "JVM internals and bytecode",
        "performance optimizations and tuning",
        "design patterns and best practices",
        "concurrency and multithreading",
        "memory management and garbage collection",
        "collections and data structures",
        "modern Java features (Java 9+)",
        "security features",
        "debugging and troubleshooting"
    };

    public LLMService(
            @Value("${langchain4j.azure-openai.api-key}") String apiKey,
            @Value("${langchain4j.azure-openai.endpoint}") String endpoint,
            @Value("${langchain4j.azure-openai.deployment-name}") String deploymentName) {

        this.model = AzureOpenAiChatModel.builder()
                .apiKey(apiKey)
                .endpoint(endpoint)
                .deploymentName(deploymentName)
                .temperature(0.7)
                .build();

        this.topicFactTemplate = PromptTemplate.from(
                "You are a Java programming expert. Generate an interesting and accurate fact about the following Java topic: {{topic}}. " +
                        "The fact should be concise, educational, and specifically about this topic. " +
                        "Return only the fact itself, without any additional text."
        );
    }

    public String generateRandomFact() {
        int randomIndex = (int) (Math.random() * javaTopics.length);
        String randomTopic = javaTopics[randomIndex];
        String prompt = String.format(
            "Generate a fascinating and specific Java programming fact about %s. " +
            "Focus on practical insights that would be valuable to Java developers. " +
            "Make it detailed and educational, but keep it concise. " +
            "Include specific examples or numbers where relevant.",
            randomTopic
        );
        return model.chat(prompt);
    }

    public String generateTopicFact(String topic) {
        String prompt = topicFactTemplate.apply(Map.of("topic", topic)).text();
        return model.chat(prompt);
    }
}