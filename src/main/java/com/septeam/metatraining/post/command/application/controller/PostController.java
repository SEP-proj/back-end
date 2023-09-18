package com.septeam.metatraining.post.command.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.septeam.metatraining.common.annotation.CustomCommonApiResponse;
import com.septeam.metatraining.common.response.ApiResponse;
import com.septeam.metatraining.post.command.application.dto.PostDTO;
import com.septeam.metatraining.post.command.application.dto.TitleDTO;
import com.septeam.metatraining.post.command.application.dto.ai.subject.GetSubjectDTO;
import com.septeam.metatraining.post.command.application.service.AIApisService;
import com.septeam.metatraining.post.command.application.service.CreatedPostService;
import com.septeam.metatraining.post.command.application.service.UpdatePostService;
import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/post")
public class PostController {

    private final CreatedPostService createdPostService;
    private final UpdatePostService updatePostService;

    private final AIApisService aiApisService;

    @Autowired
    public PostController(CreatedPostService createdPostService, UpdatePostService updatePostService, AIApisService aiApisService) {
        this.createdPostService = createdPostService;
        this.updatePostService = updatePostService;
        this.aiApisService = aiApisService;
    }

    @Operation(summary = "글쓰기 주제", description = "사용자가 글쓰기 주제를 정하면 글쓰기 주제와 카테고리 사용자 ID를 database에 저장.")
    @CustomCommonApiResponse
    @PostMapping("")
    public ResponseEntity<?> createTitle(
            @Parameter(description = "TitleDTO", required = true, example = "{\n" +
                    "  \"title\": \"string\",\n" +
                    "  \"category\": \"DAILY\",\n" +
                    "  \"memberId\": 0\n" +
                    "}")
            @RequestBody TitleDTO titleDto) {
        System.out.println("titleDto = " + titleDto);
        Post post = createdPostService.createPost(titleDto);
        ApiResponse<Post> response = new ApiResponse<>(HttpStatus.CREATED.value(), "created successfully", post);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "글쓰기 본문", description = "사용자가 작성한 본문을 저장(update)함")
    @CustomCommonApiResponse
    @PutMapping("/body")
    public ResponseEntity<?> updateBody(
            @Parameter(description = "PostDTO중 body를 받아서 사용", required = true, example = "") @RequestBody PostDTO postDTO) {
        System.out.println("PostDTO = " + postDTO);
        updatePostService.updateBody(postDTO.getId(), postDTO.getBody());
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", "saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "글쓰기 서론", description = "사용자가 작성한 서론을 저장(update)함")
    @CustomCommonApiResponse
    @PutMapping("/introduction")
    public ResponseEntity<?> updateIntroduction(
            @Parameter(description = "PostDTO중 Introduction를 받아서 사용", required = true, example = "") @RequestBody PostDTO postDTO) {
        System.out.println("PostDTO = " + postDTO);
        updatePostService.updateIntroduction(postDTO.getId(), postDTO.getIntroduction());
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", "saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "글쓰기 결론", description = "사용자가 작성한 결론을 저장(update)함")
    @CustomCommonApiResponse
    @PutMapping("/conclusion")
    public ResponseEntity<?> updateConclusion(
            @Parameter(description = "PostDTO중 Conclusion를 받아서 사용", required = true, example = "") @RequestBody PostDTO postDTO) {
        System.out.println("PostDTO = " + postDTO);
        updatePostService.updateConclusion(postDTO.getId(), postDTO.getConclusion());
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", "saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "글 합치기", description = "사용자가 작성한 사용자가 작성한 서론, 본론, 결론을 합쳐서 저장")
    @CustomCommonApiResponse
    @PutMapping("/concat")
    public ResponseEntity<?> concatWtriting(
            @Parameter(description = "PostDTO의 서론 본론 결론을 받아서 사용", required = true, example = "") @RequestBody PostDTO postDTO) {
        System.out.println("PostDTO = " + postDTO);
        String content = updatePostService.resultContent(postDTO);
        // 임시
        Map<String, String> result = new HashMap<>();
        result.put("title", "학교폭력 문제 이대로 괜찮은가?");
        result.put("content", content);
        // 임시

        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 임시
    @GetMapping("/recommend/title")
    public ResponseEntity<?> getTitle() {
        Map<String, String> result = new HashMap<>();
        result.put("title", "학교폭력은 더이상 방관하면 안된다.");
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    // 임시

    @Operation(summary = "주제 추천", description = "사용자의 요청에 AI가 글쓰기 주제 추천")
    @CustomCommonApiResponse
    @PostMapping("/recommend/subject")
    public ResponseEntity<?> recommendSubject(@RequestBody Map<String, String> category) throws JsonProcessingException {
        Object result = aiApisService.getSubjects(category);
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "카테고리추천", description = "사용자의 요청에 AI가 글 카테고리 추천")
    @CustomCommonApiResponse
    @PostMapping("/recommend/category")
    public ResponseEntity<?> recommendCategory(@RequestBody Map<String, String> subject) throws JsonProcessingException {
        System.out.println("subject = " + subject);
        Object result = aiApisService.getCategory(subject);
        System.out.println("result = " + result);
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "chatbot에게 도움요청", description = "사용자의 요청에 AI chatbot이 응답")
    @CustomCommonApiResponse
    @PostMapping("/chat/help")
    public ResponseEntity<?> chatToChatBot(@RequestBody Map<String, String> userInfo) throws JsonProcessingException {
        Object result = aiApisService.getChatAnswer(userInfo);

        System.out.println("result = " + result);

        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "chatbot에게 질문", description = "사용자의 질문에 AI chatbot이 질문에 대한 응답")
    @CustomCommonApiResponse
    @PostMapping("/chat/question")
    public ResponseEntity<?> questionToChatBot(@RequestBody Map<String, String> question) throws JsonProcessingException {
        Object result = aiApisService.getQuestionAnswer(question);
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
