package com.septeam.metatraining.post.command.application.dto;


import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class TitleDTO {
    private String title;
    private CategoryEnum category;
    private Long memberId;

    public TitleDTO(String title, CategoryEnum category, Long memberId) {
        this.title = title;
        this.category = category;
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "TitleDTO{" +
                "title='" + title + '\'' +
                ", category=" + category +
                ", memberId=" + memberId +
                '}';
    }
}
