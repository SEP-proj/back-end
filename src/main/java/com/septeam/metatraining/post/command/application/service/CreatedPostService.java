package com.septeam.metatraining.post.command.application.service;

import com.septeam.metatraining.common.response.ApiResponse;
import com.septeam.metatraining.post.command.application.dto.NoCategoryDTO;
import com.septeam.metatraining.post.command.application.dto.PostDTO;
import com.septeam.metatraining.post.command.application.dto.TitleDTO;
import com.septeam.metatraining.post.command.domain.aggregate.entity.Post;
import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import com.septeam.metatraining.post.command.domain.aggregate.vo.MemberVO;
import com.septeam.metatraining.post.command.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatedPostService {

    private final PostRepository postRepository;


    private ApiResponse apiResponse;

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

    public Post createPost(TitleDTO postDTO, String userName) {
        MemberVO memberId = MemberVO.builder().memeberId(postDTO.getMemberId()).build();
        Post newPost = new Post(
                postDTO.getSubject(),
                postDTO.getCategory(),
                memberId,
                userName
        );
        return postRepository.save(newPost);
    }

    public Post createPostNoCategory(Object category, NoCategoryDTO noCategoryDTO,String userName) {
        MemberVO memberId = MemberVO.builder().memeberId(noCategoryDTO.getMemberId()).build();
        String categoryString = category.toString();
        int start = categoryString.indexOf("{");
        int stop = categoryString.indexOf("}");
        String resultCategoryString = categoryString.substring(start + 10, stop);
        CategoryEnum resultCategory;

        switch (resultCategoryString) {
            case "일상":
                resultCategory = CategoryEnum.DAILY;
                break;
            case "과학":
                resultCategory = CategoryEnum.SCIENCE;
                break;
            case "사회":
                resultCategory = CategoryEnum.SOCIETY;
                break;
            case "환경":
                resultCategory = CategoryEnum.ENVIRONMENT;
                break;
            case "문화":
                resultCategory = CategoryEnum.CULTURE;
                break;
            case "스포츠":
                resultCategory = CategoryEnum.SPORTS;
                break;
            default:
                resultCategory = null;
                break;
        }

        Post newPost = new Post(
                noCategoryDTO.getTitle(),
                resultCategory,
                memberId,
                userName
        );
        System.out.println("newPost = " + newPost);
        return postRepository.save(newPost);
    }
}
