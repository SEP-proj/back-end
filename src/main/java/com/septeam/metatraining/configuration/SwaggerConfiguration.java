package com.septeam.metatraining.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Meta Training",
                description = "API Statement for Meta Training",
                version = "v1"
        )
)

public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi group1(){
        String[] paths = {"/v1/**"};

        return GroupedOpenApi
                .builder()
                .group("Meta Training Swagger v1")
                .pathsToMatch(paths)
                .build();
    }
}
