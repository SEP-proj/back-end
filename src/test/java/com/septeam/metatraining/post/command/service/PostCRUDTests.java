package com.septeam.metatraining.post.command.service;

import com.septeam.metatraining.post.command.application.dto.PostDTO;
import com.septeam.metatraining.post.command.application.service.CreatedPostService;
import com.septeam.metatraining.post.command.application.service.DeletePostService;
import com.septeam.metatraining.post.command.application.service.UpdatePostService;
import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import com.septeam.metatraining.post.command.domain.repository.PostRepository;
import com.septeam.metatraining.post.query.application.dto.FindPostDTO;
import com.septeam.metatraining.post.query.application.service.FindPostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class PostCRUDTests {

    @Autowired
    private CreatedPostService createdPostService;

    @Autowired
    private DeletePostService deletePostService;

    @Autowired
    private UpdatePostService updatePostService;

    @Autowired
    private FindPostService findPostService;

    @Autowired
    private PostRepository postRepository;

    private static Stream<Arguments> categoryInfo() {
        return Stream.of(
                Arguments.of(
                        new PostDTO(
                                CategoryEnum.CULTURE,
                                1L
                        )
                )
        );
    }

    private static Stream<Arguments> postInfo() {
        return Stream.of(
                Arguments.of(
                        new PostDTO(
                                "test_title",
                                CategoryEnum.CULTURE,
                                1L,
                                "test_introduction",
                                "test_body",
                                "test_conclusion",
                                "test_content",
                                false
                        )
                )
        );
    }

    @DisplayName("초기화면에서 카테고리만 정해졌을 때 DB col에 추가되는지 테스트")
    @ParameterizedTest
    @MethodSource("categoryInfo")
    void categoryCreateTest(PostDTO postDTO) {
        //when
        System.out.println("test");
        Long result = createdPostService.createPostCategory(postDTO);
        //then
        System.out.println("result = " + result);
        Assertions.assertNotNull(result);
    }

    @DisplayName("글 생성 테스트 및 삭제 테스트")
    @ParameterizedTest
    @MethodSource("postInfo")
    void create_deletePostTest(PostDTO postDTO) {
        // 글생성
        // when
        Post result = createdPostService.createPost(postDTO);
        //then
        Assertions.assertNotNull(result);

        // 글삭제
        //given
        int deleteBeforeCount = (int) postRepository.count();
        //when
        deletePostService.deletePost(result.getId());
        //then
        Assertions.assertEquals(postRepository.count(), deleteBeforeCount - 1);
    }

    @DisplayName("임시저장(update) 테스트")
    @ParameterizedTest
    @MethodSource("postInfo")
    void introductionUpdateTest(PostDTO postDTO) {
        //update전 글 생성
        // introduction
        //given
        Post createPost = createdPostService.createPost(postDTO);
        String beforeIntroduction = createPost.getIntroduction();
        String introduction = "modify introduction";
        //when
        updatePostService.updateIntroduction(createPost.getId(), introduction);
        //then
        Assertions.assertNotEquals(beforeIntroduction, createPost.getIntroduction());

        // body
        //given
        String beforeBody = createPost.getBody();
        String body = "modify body";
        //when
        updatePostService.updateBody(createPost.getId(), body);
        //then
        Assertions.assertNotEquals(beforeBody, createPost.getBody());

        // conclusion
        //given
        String beforeConclusion = createPost.getConclusion();
        String conclusion = "modify conclusion";
        //when
        updatePostService.updateConclusion(createPost.getId(), conclusion);
        //then
        Assertions.assertNotEquals(beforeConclusion, createPost.getConclusion());

        System.out.println("createPost = " + createPost);
    }

    @DisplayName("content update 테스트")
    @ParameterizedTest
    @MethodSource("postInfo")
    void contentUpdateTest(PostDTO postDTO) {

        //given
        Post createPost = createdPostService.createPost(postDTO);
        PostDTO updateComponent = new PostDTO(
                createPost.getId(),
                "modify_introduction",
                "modify_body",
                "modify_conclusion"
        );
        //when
        String content = updatePostService.resultContent(updateComponent);
        //then
        Assertions.assertNotNull(content);
    }

    @DisplayName("post 최종 update 테스트")
    @ParameterizedTest
    @MethodSource("postInfo")
    void postUpdateTest(PostDTO postDTO) {

        //given
        Post createPost = createdPostService.createPost(postDTO);
        String beforeTitle = createPost.getTitle();
        String beforeContent = createPost.getContent();
        boolean beforePublished = createPost.isPublished();
        PostDTO updatePost = new PostDTO(
                createPost.getId(),
                "modify_title",
                "modify_content",
                true
        );
        //when
        updatePostService.resultPost(updatePost);
        //then
        Assertions.assertNotEquals(beforeTitle, createPost.getTitle());
        Assertions.assertNotEquals(beforeContent, createPost.getContent());
        Assertions.assertNotEquals(beforePublished, createPost.isPublished());
    }


    // 조회 테스트 (Mybatis) smaple data가 있는 상황에서 진행
    @DisplayName("전체 글 조회 테스트")
    @Test
    void findAllTest(){
        //when
        List<Post> posts = findPostService.findAll();
        //then
        Assertions.assertDoesNotThrow(() -> findPostService.findAll());
        Assertions.assertNotNull(posts);
    }

    @DisplayName("글 카테고리 별 조회 테스트 ")
    @Test
    void findByCategory(){
        //given
        String category = "DAILY";
        //when
        List<Post> posts = findPostService.findByCategory(category);
        //then
        Assertions.assertDoesNotThrow(()-> findPostService.findByCategory(category));
        Assertions.assertNotNull(posts);
    }

    @DisplayName("내가 작성한 글 조회 테스트")
    @Test
    void findByMyPost(){
        //given
        Long memberId= 1L;
        //when
        List<Post> myPosts = findPostService.findByMyPost(memberId);
        System.out.println("myPosts = " + myPosts);
        //then
        Assertions.assertDoesNotThrow(() -> findPostService.findByMyPost(memberId));
        Assertions.assertNotNull(myPosts);
    }

    @DisplayName("글 상세보기 요청 테스트")
    @Test
    void findById(){
        //given
        Long postId = 1L;
        //when
        Post post = findPostService.findById(postId);
        //then
        Assertions.assertDoesNotThrow(()->findPostService.findById(postId));
        Assertions.assertNotNull(post);
    }
}
