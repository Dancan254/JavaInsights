//package com.javainsights.controller;
//
//import com.javainsights.model.JavaFact;
//import com.javainsights.service.JavaFactsService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(JavaFactsController.class)
//public class JavaFactsControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private JavaFactsService javaFactsService;
//
//    @Test
//    void getRandomFact_ShouldReturnFact() throws Exception {
//        JavaFact mockFact = new JavaFact("Random", "Java uses JIT compilation for better performance");
//        when(javaFactsService.getRandomFact()).thenReturn(mockFact);
//
//        mockMvc.perform(get("/api/facts/random"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.topic").value("Random"))
//                .andExpect(jsonPath("$.fact").value("Java uses JIT compilation for better performance"));
//    }
//
//    @Test
//    void getTopicFact_WithValidTopic_ShouldReturnFact() throws Exception {
//        JavaFact mockFact = new JavaFact("Inheritance", "Java supports multiple interface inheritance but not multiple class inheritance");
//        when(javaFactsService.getTopicFact("Inheritance")).thenReturn(mockFact);
//
//        mockMvc.perform(get("/api/facts/topic").param("name", "Inheritance"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.topic").value("Inheritance"))
//                .andExpect(jsonPath("$.fact").value("Java supports multiple interface inheritance but not multiple class inheritance"));
//    }
//
//    @Test
//    void getTopicFact_WithEmptyTopic_ShouldReturnBadRequest() throws Exception {
//        mockMvc.perform(get("/api/facts/topic").param("name", ""))
//                .andExpect(status().isBadRequest());
//    }
//}
