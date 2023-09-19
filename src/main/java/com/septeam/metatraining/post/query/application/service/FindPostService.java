package com.septeam.metatraining.post.query.application.service;

import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import com.septeam.metatraining.post.query.domain.repository.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return postMapper.findAll();
    }

    // 카테고리별 조회
    public List<Post> findByCategory(String category){
        // 카테고리를 한글로 넘기면 여기서 변환해줘야함
        return postMapper.findByCategory(category);
    }

    // 글 상세보기
    public Post findById(Long postId){
        return postMapper.findById(postId);
    }

    // 내가 쓴 글 조회
    public List<Post> findByMyPost(Long memberId){
        return postMapper.findByMyPost(memberId);
    }


}
