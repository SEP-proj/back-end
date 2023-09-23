package com.septeam.metatraining.post.command.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.septeam.metatraining.post.command.application.dto.ai.subject.CategoryDTO;
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

    public ResponseEntity<Object> getQuestionAnswer(Map<String, String> question) {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("content", question.get("content"));
        return webClient
                .post()
                .uri("/chatbot/make_question")
                .bodyValue(bodyMap)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }

    public ResponseEntity<Object> getTitle(Map<String, String> content){
        Map<String, String> bodyMap =new HashMap<>();
        bodyMap.put("content", content.get("content"));
        System.out.println("bodyMap = " + bodyMap);
        return webClient
                .post()
                .uri("/modification/check_content")
                .bodyValue(bodyMap)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }

    //    ===========================================================================================================================================================
    public ResponseEntity<Object> feedback(/*CategoryEnum categoryEnum*/) throws JsonProcessingException {
        System.out.println("서비스 통과!");
        CategoryDTO bodyData = new CategoryDTO("category", "환경");
        ObjectMapper objectMapper = new ObjectMapper();
        String bodyJson = objectMapper.writeValueAsString(bodyData);

        Map<String, Object> bodyMap = new HashMap<>();
//        Map<String, String> objectMap = new HashMap<>();
//        bodyMap.put("category", objectMap.put("1","환경"));
        bodyMap.put("content", "비혼주의의 확산^최근 비혼주의라는 말이 많이 나오고 있다. 나는 개인적으로 비혼주의에 대해 찬성한다. 왜냐하면 결혼 여부를 결정하는 것은 주변의 시선이 아닌 나의 선택이기 때문입니다. 예를 들어 학업, 취미 등에 더 집중하게 되어 개인의 삶의 질을 높일 수 있습니다.  그리고 결혼이라는 중대한 이벤트에 대한 경제적 부담이 줄어듭니다.");
        return webClient
                .post()
                .uri("/modification/check_content")
//                .uri("/topic_recommand/for_server")
                .bodyValue(bodyMap)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }

    public ResponseEntity<Object> requestFeedback(/*CategoryEnum categoryEnum*/) throws JsonProcessingException {
        System.out.println("서비스 통과!");
        CategoryDTO bodyData = new CategoryDTO("category", "환경");
        ObjectMapper objectMapper = new ObjectMapper();
        String bodyJson = objectMapper.writeValueAsString(bodyData);

        Map<String, Object> bodyMap = new HashMap<>();
//        Map<String, String> objectMap = new HashMap<>();
//        bodyMap.put("category", objectMap.put("1","환경"));
        bodyMap.put("content", "비혼주의의 확산^최근 비혼주의라는 말이 많이 나오고 있다. 나는 개인적으로 비혼주의에 대해 찬성한다. 왜냐하면 결혼 여부를 결정하는 것은 주변의 시선이 아닌 나의 선택이기 때문입니다. 예를 들어 학업, 취미 등에 더 집중하게 되어 개인의 삶의 질을 높일 수 있습니다.  그리고 결혼이라는 중대한 이벤트에 대한 경제적 부담이 줄어듭니다.");
        bodyMap.put("user_input", "더 이상 생각이 안나ㅠㅠㅠㅠㅠ");

        return webClient
                .post()
                .uri("/chatbot/chat")
//                .uri("/topic_recommand/for_server")
                .bodyValue(bodyMap)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }


}
