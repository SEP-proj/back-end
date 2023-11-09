package com.septeam.metatraining.post.command.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.septeam.metatraining.common.annotation.CustomCommonApiResponse;
import com.septeam.metatraining.common.response.ApiResponse;
import com.septeam.metatraining.post.command.application.dto.NoCategoryDTO;
import com.septeam.metatraining.post.command.application.dto.PostDTO;
import com.septeam.metatraining.post.command.application.dto.TitleDTO;
import com.septeam.metatraining.post.command.application.dto.ai.subject.GetSubjectDTO;
import com.septeam.metatraining.post.command.application.service.AIApisService;
import com.septeam.metatraining.post.command.application.service.CreatedPostService;
import com.septeam.metatraining.post.command.application.service.UpdatePostService;
import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import com.septeam.metatraining.security.Principal.UserPrincipal;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<?> createTitle(@AuthenticationPrincipal UserPrincipal userPrincipal,
            @Parameter(description = "TitleDTO", required = true, example = "{\n" +
                    "  \"title\": \"string\",\n" +
                    "  \"category\": \"DAILY\",\n" +
                    "  \"memberId\": 0\n" +
                    "}")
            @RequestBody TitleDTO titleDto) {
        System.out.println("titleDto = " + titleDto);
        String userName = userPrincipal.getName();
        System.out.println("userName = " + userName);
        Post post = createdPostService.createPost(titleDto,userName);
        System.out.println("post = " + post);
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "created successfully", post);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "글쓰기 주제", description = "사용자가 글쓰기 주제를 정하면 글쓰기 주제와 카테고리 사용자 ID를 database에 저장.")
    @CustomCommonApiResponse
    @PostMapping("/test")
    public ResponseEntity<?> createTitleNoCategory(@AuthenticationPrincipal UserPrincipal userPrincipal,
            @Parameter(description = "TitleDTO", required = true, example = "{\n" +
                    "  \"title\": \"string\",\n" +
                    "  \"category\": \"DAILY\",\n" +
                    "  \"memberId\": 0\n" +
                    "}")
            @RequestBody NoCategoryDTO noCategoryDTO) {
        System.out.println("noCategoryDTO = " + noCategoryDTO);
        Object category = aiApisService.getCategoryString(noCategoryDTO.getTitle());
        System.out.println("category = " + category);
        String userName = userPrincipal.getName();
        System.out.println("userName = " + userName);
        Post post = createdPostService.createPostNoCategory(category, noCategoryDTO, userName);
        System.out.println("post = " + post);
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

    @Operation(summary = "임시저장 통합", description = "사용자가 작성한 글을 저장(update)함")
    @CustomCommonApiResponse
    @PutMapping("/update")
    public ResponseEntity<?> updateAll(
            @Parameter(description = "PostDTO중 서론,본론, 결론 받아서 사용", required = true, example = "") @RequestBody PostDTO postDTO) {
        System.out.println("PostDTO = " + postDTO);
        Post updatePost = updatePostService.updateAll(postDTO);
        String content = postDTO.getIntroduction() + "\n" +
                postDTO.getBody() + "\n" +
                postDTO.getConclusion();
        Map<String, String> result = new HashMap<>();
        result.put("content",content);
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully",  result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "글 합치기", description = "사용자가 작성한 사용자가 작성한 서론, 본론, 결론을 합쳐서 저장")
    @CustomCommonApiResponse
    @PutMapping("/concat")
    public ResponseEntity<?> concatWtriting(
            @Parameter(description = "PostDTO의 서론 본론 결론을 받아서 사용", required = true, example = "") @RequestBody PostDTO postDTO) {
        System.out.println("PostDTO = " + postDTO);

        String content = updatePostService.resultContent(postDTO);
        System.out.println("content = " + content);
        Object title = aiApisService.getTitleString(content);
        System.out.println("title = " + title);
        Map<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("content", content);

        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "글 발행 여부 확인", description = "사용자가 최종적으로 피드백을 마친 글 저장")
    @CustomCommonApiResponse
    @PutMapping("/final")
    public ResponseEntity<?> resultPost(
            @Parameter(description = "PostDTO의 피드백 후 content, 발행여부 받아서 사용", required = true, example = "") @RequestBody PostDTO postDTO
    ) {
        System.out.println("postDTO = " + postDTO);
        updatePostService.resultPost(postDTO);
        Map<String, String> result = new HashMap<>();
        result.put("result", "성공");
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "제목 추천", description = "사용자의 요청에 AI가 글쓰기 제목 추천")
    @CustomCommonApiResponse
    @PostMapping("/recommend/title")
    public ResponseEntity<?> getTitle(
            @Parameter(description = "content을 기반으로 title 추천", required = true, example = "") @RequestBody Map<String, String> content
    ) {
        Object result = aiApisService.getTitle(content);
        System.out.println("result = " + result);
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "주제 추천", description = "사용자의 요청에 AI가 글쓰기 주제 추천")
    @CustomCommonApiResponse
    @PostMapping("/recommend/subject")
    public ResponseEntity<?> recommendSubject(@RequestBody Map<String, String> category) throws JsonProcessingException {
        System.out.println("category = " + category);
        Object result = aiApisService.getSubjects(category);
        System.out.println("result = " + result);
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


    // 수정 => 글이 합쳐지지 않은 상태에서 질문을 생성할 수 있게 서론,본론,결론 나눠서 받아야함
    @Operation(summary = "chatbot에게 질문", description = "사용자의 질문에 AI chatbot이 질문에 대한 응답")
    @CustomCommonApiResponse
    @PostMapping("/chat/question")
    public ResponseEntity<?> questionToChatBot(@RequestBody PostDTO postDTO) {
        Object result = aiApisService.getQuestionAnswer(postDTO);
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "chatbot에게 feedback 요청", description = "사용자의 요청에 AI가 피드백 응답")
    @CustomCommonApiResponse
    @PostMapping("/chat/feedback")
    public ResponseEntity<?> feedbackToChatBot(
            @RequestBody PostDTO postDTO
    ){
        System.out.println("피드백 메소드");
        System.out.println("postDTO = " + postDTO);
        Object result = aiApisService.getFeedBack(postDTO);
        System.out.println("result = " + result);
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}