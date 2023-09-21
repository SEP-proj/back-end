package com.septeam.metatraining.post.query.service;

import com.septeam.metatraining.post.command.application.dto.PostDTO;
import com.septeam.metatraining.post.command.application.service.CreatedPostService;
import com.septeam.metatraining.post.command.application.service.DeletePostService;
import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import com.septeam.metatraining.post.query.application.dto.FindPostDTO;
import com.septeam.metatraining.post.query.application.service.FindPostService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class PostReadTests {

    @Autowired
    private CreatedPostService createdPostService;

    @Autowired
    private DeletePostService deletePostService;

    @Autowired
    private FindPostService findPostService;

    private static Stream<Arguments> postInfo() {
        return Stream.of(
                Arguments.of(
                        new PostDTO(
                                "test_title_1",
                                CategoryEnum.CULTURE,
                                1L,
                                "test_introduction_1",
                                "test_body",
                                "test_conclusion_1",
                                "test_content_1",
                                true
                        ),
                        new PostDTO(
                                "test_title_2",
                                CategoryEnum.DAILY,
                                1L,
                                "test_introduction_2",
                                "test_body_2",
                                "test_conclusion_2",
                                "test_content_2",
                                true
                        )
                )
        );
    }

    Long postId_1;
    Long postId_2;

    @AfterEach
    void deletePost() {
        if (postId_2 != null) {
            deletePostService.deletePost(postId_1);
            deletePostService.deletePost(postId_2);
        } else {
            deletePostService.deletePost(postId_1);
        }
        postId_1 = null;
        postId_2 = null;
    }


    @DisplayName("글 카테고리별 조회 테스트")
    @ParameterizedTest
    @MethodSource("postInfo")
    void findByCategoryTest(PostDTO postDTO) {
        // given
        Post post = createdPostService.createPost(postDTO);
        postId_1 = post.getId();
        String category = String.valueOf(post.getCategory());
        //when
        List<FindPostDTO> findPosts = findPostService.findByCategory(String.valueOf(post.getCategory()));
        //then
        Assertions.assertNotNull(findPosts);
    }

    @DisplayName("글 전체 조회 테스트")
    @ParameterizedTest
    @MethodSource("postInfo")
    void findAllTest(PostDTO postDTO1, PostDTO postDTO2) {
        // given
        int beforeSize = findPostService.findAll().size();
        Post post1 = createdPostService.createPost(postDTO1);
        Post post2 = createdPostService.createPost(postDTO2);
        postId_1 = post1.getId();
        postId_2 = post2.getId(); // 삭제를 위한 식별자 값 저장

        //when
        List<FindPostDTO> findPosts = findPostService.findAll();
        int afterSize = findPosts.size();

        //then
        Assertions.assertNotNull(findPosts);
        Assertions.assertNotEquals(beforeSize, afterSize);
        Assertions.assertEquals(beforeSize + 2, afterSize);
    }

    @DisplayName("내가 작성한 글 조회 테스트")
    @ParameterizedTest
    @MethodSource("postInfo")
    void findByMyPostTest(PostDTO postDTO1, PostDTO postDTO2) {
        // given
        Post post1 = createdPostService.createPost(postDTO1);
        Post post2 = createdPostService.createPost(postDTO2);
        postId_1 = post1.getId();
        postId_2 = post2.getId();
        //when
        List<FindPostDTO> findPosts = findPostService.findByMyPost(postDTO1.getMemberId());
        //then
        Assertions.assertNotNull(findPosts);
        Assertions.assertEquals(2, findPosts.size());
    }

    @DisplayName("글 상세보기 조회 테스트")
    @ParameterizedTest
    @MethodSource("postInfo")
    void findByIdTest(PostDTO postDTO) {
        // given
        Post post = createdPostService.createPost(postDTO);
        postId_1 = post.getId();
        //when
        FindPostDTO findPost = findPostService.findById(post.getId());
        //then
        Assertions.assertNotNull(findPost);
    }

}
