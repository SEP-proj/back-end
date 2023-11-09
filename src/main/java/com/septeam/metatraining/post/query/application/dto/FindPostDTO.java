package com.septeam.metatraining.post.query.application.dto;

import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FindPostDTO {

    private Long id;

    private String title;

    private CategoryEnum category;

    private Long memberId;

    private String introduction;

    private String body;

    private String conclusion;

    private String subject;

    private String content;

    private LocalDateTime createDate;

    private String memberName;

    private boolean published;

    public FindPostDTO() {}


    public FindPostDTO(Long id, String title, CategoryEnum category, Long memberId, String introduction, String body, String conclusion, String subject, String content, LocalDateTime createDate, String memberName, boolean published) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.memberId = memberId;
        this.introduction = introduction;
        this.body = body;
        this.conclusion = conclusion;
        this.subject = subject;
        this.content = content;
        this.createDate = createDate;
        this.memberName = memberName;
        this.published = published;
    }
}
