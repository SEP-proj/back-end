package com.septeam.metatraining.post.command.application.dto.ai.subject;

import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class GetSubjectDTO {
    private ArrayList<String> subjects;
}
