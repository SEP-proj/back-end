package com.septeam.metatraining.post.command.application.dto.ai.subject;

import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import lombok.Data;
import java.util.ArrayList;

@Data
public class GetSubjectDTO {
    private ArrayList<String> subjects;
}
