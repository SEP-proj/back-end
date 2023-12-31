package com.septeam.metatraining.post.command.application.dto;


import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class TitleDTO {
    private String subject;
    private CategoryEnum category;
    private Long memberId;

    public TitleDTO(String subject, CategoryEnum category, Long memberId) {
        this.subject = subject;
        this.category = category;
        this.memberId = memberId;
    }


    @Override
    public String toString() {
        return "TitleDTO{" +
                "subject='" + subject + '\'' +
                ", category=" + category +
                ", memberId=" + memberId +
                '}';
    }
}
