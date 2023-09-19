package com.septeam.metatraining.post.query.domain.repository;

import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> findAll();

    List<Post> findByCategory(String category);

    Post findById(Long postId);

    List<Post> findByMyPost(Long memberId);
}
