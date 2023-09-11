package com.septeam.metatraining.post.command.application.service;

import com.septeam.metatraining.post.command.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    @Autowired
    public DeletePostService(PostRepository postRepository){
        this.postRepository=postRepository;
    }

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }
}
