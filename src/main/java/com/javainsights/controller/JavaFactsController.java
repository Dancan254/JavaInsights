package com.javainsights.controller;

import com.javainsights.model.JavaFact;
import com.javainsights.service.JavaFactsService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facts")
@RequiredArgsConstructor
@Validated
public class JavaFactsController {
    private final JavaFactsService javaFactsService;

    @GetMapping("/random")
    public ResponseEntity<JavaFact> getRandomFact() {
        return ResponseEntity.ok(javaFactsService.getRandomFact());
    }

    @GetMapping("/topic")
    public ResponseEntity<JavaFact> getTopicFact(
            @RequestParam @NotBlank(message = "Topic cannot be empty") String name) {
        return ResponseEntity.ok(javaFactsService.getTopicFact(name));
    }
}
