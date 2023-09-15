package com.septeam.metatraining.post.command.application.dto.ai.subject;

import lombok.Data;

@Data
public class CategoryDTO {

    private String key;
    private String value;


    public CategoryDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
