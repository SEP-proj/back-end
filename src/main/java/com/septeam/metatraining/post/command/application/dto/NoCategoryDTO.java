package com.septeam.metatraining.post.command.application.dto;

import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoCategoryDTO {
    private String title;
    private Long memberId;

    public NoCategoryDTO(String title, Long memberId) {
        this.title = title;
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "NoCategoryDTO{" +
                "title='" + title + '\'' +
                ", memberId=" + memberId +
                '}';
    }
}
