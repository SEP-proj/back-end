package com.septeam.metatraining.post.command.application.dto;

import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PostDTO {

    private Long id;
    private String title;
    private CategoryEnum category;
    private Long memberId;
    private String introduction;
    private String body;
    private String conclusion;
    private String content;
    private boolean published;

    public PostDTO(Long id, String title, CategoryEnum category, Long memberId, String introduction, String body, String conclusion, String content, boolean published) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.memberId = memberId;
        this.introduction = introduction;
        this.body = body;
        this.conclusion = conclusion;
        this.content = content;
        this.published = published;
    }

    // create
    public PostDTO(CategoryEnum category, Long memberId) {
        this.category = category;
        this.memberId = memberId;
    }

    // create
    public PostDTO(String title, CategoryEnum category, Long memberId, String introduction, String body, String conclusion, String content, boolean published) {
        this.title = title;
        this.category = category;
        this.memberId = memberId;
        this.introduction = introduction;
        this.body = body;
        this.conclusion = conclusion;
        this.content = content;
        this.published = published;
    }

    public PostDTO(Long id, String introduction, String body, String conclusion) {
        this.id = id;
        this.introduction = introduction;
        this.body = body;
        this.conclusion = conclusion;
    }

    public PostDTO(Long id, String title, String content, boolean published){
        this.id=id;
        this.title=title;
        this.content=content;
        this.published=published;
    }
}
