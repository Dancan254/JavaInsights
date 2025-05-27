package com.javainsights.controller;

import com.javainsights.model.JavaFact;
import com.javainsights.service.JavaFactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class WebController {
    
    private final JavaFactsService javaFactsService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/random")
    public String home(Model model) {
        JavaFact fact = javaFactsService.getRandomFact();
        model.addAttribute("fact", fact);
        return "fact-view";
    }

    @GetMapping("/api/random-fact")
    @ResponseBody
    public JavaFact getRandomFactJson() {
        return javaFactsService.getRandomFact();
    }

    @PostMapping("/topic")
    public String getTopicFact(@RequestParam String topic, Model model) {
        JavaFact fact = javaFactsService.getTopicFact(topic);
        model.addAttribute("fact", fact);
        model.addAttribute("searchedTopic", topic);
        return "fact-view";
    }
}

