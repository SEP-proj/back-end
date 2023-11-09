package com.septeam.metatraining.post.command.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.septeam.metatraining.post.command.application.dto.PostDTO;
import com.septeam.metatraining.post.command.application.dto.ai.subject.CategoryDTO;
import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class AIApisService {
    private final WebClient webClient;


    @Autowired
    public AIApisService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseEntity<Object> getSubjects(Map<String, String> category) throws JsonProcessingException {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("category", category.get("category"));
        System.out.println("bodyMap = " + bodyMap);
        return webClient
                .post()
                .uri("/topic_recommand/make_subject")
                .bodyValue(bodyMap)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }

    public ResponseEntity<Object> getCategory(Map<String, String> subject) {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("subject", subject.get("subject"));
        return webClient
                .post()
                .uri("/topic_recommand/make_category")
                .bodyValue(bodyMap)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }

    public ResponseEntity<Object> getCategoryString(String subject) {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("subject", subject);
        return webClient
                .post()
                .uri("/topic_recommand/make_category")
                .bodyValue(bodyMap)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }

    public ResponseEntity<Object> getChatAnswer(Map<String, String> userInfo) {
        Map<String, String> bodyMap = new HashMap<>();
        System.out.println("userInfo = " + userInfo);
        String content = userInfo.get("introduction") + "^" + userInfo.get("body") + "^" + userInfo.get("conclusion");
        bodyMap.put("content", content);
        bodyMap.put("user_input", userInfo.get("user_input"));
        System.out.println("bodyMap = " + bodyMap);
        return webClient
                .post()
                .uri("/chatbot/chat")
                .bodyValue(bodyMap)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }

    public ResponseEntity<Object> getQuestionAnswer(PostDTO postDTO) {
        String content = postDTO.getIntroduction() + "^" + postDTO.getBody() + "^" + postDTO.getConclusion();

        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("content", content);
        return webClient
                .post()
                .uri("/chatbot/make_question")
                .bodyValue(bodyMap)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }

    public ResponseEntity<Object> getTitle(Map<String, String> content) {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("content", content.get("content"));
        System.out.println("bodyMap = " + bodyMap);
        return webClient
                .post()
                .uri("/modification/make_title")
                .bodyValue(bodyMap)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }

    public ResponseEntity<Object> getTitleString(String content) {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("content", content);
        return webClient
                .post()
                .uri("/modification/make_title")
                .bodyValue(bodyMap)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }

    public ResponseEntity<Object> getFeedBack(PostDTO postDTO) {
        System.out.println("postDTO.getContent() = " + postDTO.getContent());
        String[] content = postDTO.getContent().split("\n");
        String resultContent = content[0] + "^" + content[1] + "^" + content[2];
        Map<String, String> bodyMap = new HashMap<>();

        bodyMap.put("content", resultContent);
        return webClient
                .post()
                .uri("/modification/check_content")
                .bodyValue(bodyMap)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }
}
