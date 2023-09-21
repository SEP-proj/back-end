package com.septeam.metatraining.post.query.application.controller;


import com.septeam.metatraining.common.annotation.CustomCommonApiResponse;
import com.septeam.metatraining.common.response.ApiResponse;
import com.septeam.metatraining.post.command.application.dto.PostDTO;
import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import com.septeam.metatraining.post.query.application.dto.FindPostDTO;
import com.septeam.metatraining.post.query.application.service.FindPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/post")
public class FindPostController {

    private final FindPostService findPostService;

    @Autowired
    public FindPostController(FindPostService findPostService) {
        this.findPostService = findPostService;
    }

    @Operation(summary = "전체 글 조회", description = "사용자의 요청에 발행된 전체 글 조회")
    @CustomCommonApiResponse
    @GetMapping("/all")
    public ResponseEntity<?> findAllPost(){
        List<FindPostDTO> posts = findPostService.findAll();
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CHECKPOINT.value(), "saved successfully", posts);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @Operation(summary = "카테고리 별 조회", description = "사용자의 요청에 의해 카테고리 별 글 조회")
    @CustomCommonApiResponse
    @PostMapping("/category")
    public ResponseEntity<?> findByCategory(
            @Parameter(description = "PostDTO 중 category 받아서 글 조회시 사용",required = true, example = "")@RequestBody PostDTO postDTO
            ){
        System.out.println("postDTO = " + postDTO);
        List<FindPostDTO> posts = findPostService.findByCategory(String.valueOf(postDTO.getCategory()));
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", posts);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @Operation(summary = "내가 작성한 글 전체 조회", description = "사용자의 요청에 의해 내가 작성한 글 조회")
    @CustomCommonApiResponse
    @PostMapping("/mypost")
    public ResponseEntity<?> findByMyPost(
            @Parameter(description = "PostDTO 중 member_id 받아서 글 조회시 사용", required = true, example = "") @RequestBody PostDTO postDTO
    ){
        System.out.println("postDTO = " + postDTO);
        List<FindPostDTO> posts = findPostService.findByMyPost(postDTO.getMemberId());
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", posts);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "글 상세보기", description = "사용자의 요청에 의해 글 한개 조회")
    @CustomCommonApiResponse
    @GetMapping("/{postId}")
    public ResponseEntity<?> findById(@PathVariable Long postId){
        System.out.println("postId = " + postId);
        FindPostDTO post = findPostService.findById(postId);
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.CREATED.value(),"saved successfully", post);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
