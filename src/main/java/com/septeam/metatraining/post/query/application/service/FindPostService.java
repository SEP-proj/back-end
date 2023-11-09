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
    public List<FindPostDTO> findAll(){
        return postMapper.findAll();
    }

    // 카테고리별 조회
    public List<FindPostDTO> findByCategory(String category){
        return postMapper.findByCategory(category);
    }

    // 글 상세보기
    public FindPostDTO findById(Long postId){
        return postMapper.findById(postId);
    }

    // 내가 쓴 글 조회
    public List<FindPostDTO> findByMyPost(Long memberId){
        return postMapper.findByMyPost(memberId);
    }

}
