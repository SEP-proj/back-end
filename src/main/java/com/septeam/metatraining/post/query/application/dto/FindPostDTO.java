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

    private String content;

    private LocalDateTime createDate;

    private boolean published;

    public FindPostDTO() {}


    @Override
    public String toString() {
        return "FindPostDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", memberId=" + memberId +
                ", introduction='" + introduction + '\'' +
                ", body='" + body + '\'' +
                ", conclusion='" + conclusion + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", published=" + published +
                '}';
    }
}
