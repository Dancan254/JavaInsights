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
    private final PromptTemplate randomFactTemplate;
    private final PromptTemplate topicFactTemplate;

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

        this.randomFactTemplate = PromptTemplate.from(
                "You are a Java programming expert. Generate an interesting and accurate fact about Java programming. " +
                        "The fact should be concise, educational, and focus on a random aspect of Java. " +
                        "Return only the fact itself, without any additional text."
        );

        this.topicFactTemplate = PromptTemplate.from(
                "You are a Java programming expert. Generate an interesting and accurate fact about the following Java topic: {{topic}}. " +
                        "The fact should be concise, educational, and specifically about this topic. " +
                        "Return only the fact itself, without any additional text."
        );
    }

    public String generateRandomFact() {
        String prompt = randomFactTemplate.apply(Map.of()).text();
        return model.chat(prompt);
    }

    public String generateTopicFact(String topic) {
        String prompt = topicFactTemplate.apply(Map.of("topic", topic)).text();
        return model.chat(prompt);
    }
}