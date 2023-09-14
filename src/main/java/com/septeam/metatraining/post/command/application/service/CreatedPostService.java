package com.septeam.metatraining.post.command.application.service;

import com.septeam.metatraining.post.command.application.dto.PostDTO;
import com.septeam.metatraining.post.command.application.dto.TitleDTO;
import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import com.septeam.metatraining.post.command.domain.aggregate.vo.MemberVO;
import com.septeam.metatraining.post.command.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatedPostService {

    private final PostRepository postRepository;

    @Autowired
    public CreatedPostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public Long createPostCategory(PostDTO postDTO) {
        MemberVO memberId = MemberVO.builder().memeberId(postDTO.getMemberId()).build();
        Post newPost = new Post(
                postDTO.getCategory(),
                memberId
        );
        Post resultPost = postRepository.save(newPost);
        return resultPost.getId();
    }

    public Post createPost(PostDTO postDTO) {
        MemberVO memberId = MemberVO.builder().memeberId(postDTO.getMemberId()).build();
        Post newPost = new Post(
                postDTO.getTitle(),
                postDTO.getCategory(),
                memberId,
                postDTO.getIntroduction(),
                postDTO.getBody(),
                postDTO.getConclusion(),
                postDTO.getContent(),
                postDTO.isPublished()
        );
        return postRepository.save(newPost);
    }

    public Post createPost(TitleDTO postDTO) {
        MemberVO memberId = MemberVO.builder().memeberId(postDTO.getMemberId()).build();
        Post newPost = new Post(
                postDTO.getTitle(),
                postDTO.getCategory(),
                memberId
        );
        return postRepository.save(newPost);
    }
}
