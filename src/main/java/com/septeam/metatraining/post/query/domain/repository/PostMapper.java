package com.septeam.metatraining.post.query.domain.repository;

import com.septeam.metatraining.post.command.application.dto.PostDTO;
import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import com.septeam.metatraining.post.query.application.dto.FindPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<FindPostDTO> findAll();

    List<FindPostDTO> findByCategory(String category);

    FindPostDTO findById(Long postId);

    List<FindPostDTO> findByMyPost(Long memberId);
}
