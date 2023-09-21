package com.septeam.metatraining.post.query.application.service;

import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import com.septeam.metatraining.post.query.application.dto.FindPostDTO;
import com.septeam.metatraining.post.query.domain.repository.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindPostService {

    private final PostMapper postMapper;

    @Autowired
    public FindPostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }
    // 전부 published가 true인 즉, 발행된 글만

    // 글 전체 조회
    public List<Post> findAll(){
        List<FindPostDTO> postDTOS = postMapper.findAll();

        List<Post> posts = new ArrayList<>();
        for (FindPostDTO postDTO: postDTOS ){
            posts.add(Post.dtoToEntity(postDTO));
        }
        return posts;
    }

    // 카테고리별 조회
    public List<Post> findByCategory(String category){
        List<FindPostDTO> postDTOS = postMapper.findByCategory(category);

        List<Post> posts = new ArrayList<>();
        for (FindPostDTO postDTO: postDTOS ){
            posts.add(Post.dtoToEntity(postDTO));
        }
        return posts;
    }

    // 글 상세보기
    public Post findById(Long postId){
        FindPostDTO postDTO= postMapper.findById(postId);

        Post post = Post.dtoToEntity(postDTO);

        return post;
    }

    // 내가 쓴 글 조회
    public List<Post> findByMyPost(Long memberId){
        List<FindPostDTO> postDTOS = postMapper.findByMyPost(memberId);

        List<Post> posts = new ArrayList<>();
        for (FindPostDTO postDTO: postDTOS ){
            posts.add(Post.dtoToEntity(postDTO));
        }
        return posts;
    }


}
