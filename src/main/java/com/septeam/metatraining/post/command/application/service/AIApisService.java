package com.septeam.metatraining.post.command.application.service;

import com.septeam.metatraining.post.command.application.dto.ai.subject.GetSubjectDTO;
import com.septeam.metatraining.post.command.domain.aggregate.entity.enumType.CategoryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AIApisService {
    private final WebClient webClient;


    @Autowired
    public AIApisService(WebClient webClient) {
        this.webClient = webClient;
    }
    public ResponseEntity<GetSubjectDTO> getSubjects(/*CategoryEnum categoryEnum*/)  {
        String category = "환경";
        return webClient
                .post()
                .uri("/topic_recommand/make_subject")
                .bodyValue(category)
                .retrieve()
                .toEntity(GetSubjectDTO.class)
                .block();
    }

}
