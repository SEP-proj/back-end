package com.septeam.metatraining.post.command.application.dto.ai.subject;

import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import lombok.Data;

@Data
public class GetCategoryDTO {
    private CategoryEnum category;
}
