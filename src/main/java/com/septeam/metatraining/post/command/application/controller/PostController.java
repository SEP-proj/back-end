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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", content);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/recommand")
    public ResponseEntity<?> getSubject() throws JsonProcessingException {
        Object result = aiApisService.getSubjects();
        System.out.println("result = " + result.toString());
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", result);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}