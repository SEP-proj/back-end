package com.septeam.metatraining.post.command.application.service;

import com.septeam.metatraining.post.command.application.dto.postDTO;
import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import com.septeam.metatraining.post.command.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdatePostService {

    private final PostRepository postRepository;

    @Autowired
    public UpdatePostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public void updateIntroduction(Long postId, String introduction) {
        Optional<Post> post = postRepository.findById(postId);
        post.get().setIntroduction(introduction);
        Post updatePost = postRepository.save(post.get());
    }
    @Transactional
    public void updateBody(Long postId, String body) {
        Optional<Post> post = postRepository.findById(postId);
        post.get().setBody(body);
        Post updatePost = postRepository.save(post.get());
    }
    @Transactional
    public void updateConclusion(Long postId, String conclusion) {
        Optional<Post> post = postRepository.findById(postId);
        post.get().setConclusion(conclusion);
        Post updatePost = postRepository.save(post.get());
    }

    // 마지막 페이지로 넘어갈 때
    public String resultContent(postDTO postDTO){
        Optional<Post> post = postRepository.findById(postDTO.getId());

        String content=postDTO.getIntroduction()+"\n"+
                postDTO.getBody()+"\n"+
                postDTO.getConclusion();

        Post updatePost= new Post(
                postDTO.getId(),
                post.get().getTitle(),
                post.get().getCategory(),
                post.get().getMemberId(),
                postDTO.getIntroduction(),
                postDTO.getBody(),
                postDTO.getConclusion(),
                content
        );

        postRepository.save(updatePost);
        return content;
    }

    // 마지막 페이지에서 요청처리
    public void resultPost(postDTO postDTO){
        Optional<Post> post = postRepository.findById(postDTO.getId());

        Post resultPost= new Post(
                postDTO.getId(),
                postDTO.getTitle(),
                post.get().getCategory(),
                post.get().getMemberId(),
                post.get().getIntroduction(),
                post.get().getBody(),
                post.get().getConclusion(),
                postDTO.getContent(),
                postDTO.isPublished()
        );
        postRepository.save(resultPost);
    }
}