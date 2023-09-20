package com.septeam.metatraining;

import com.septeam.metatraining.configuration.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)

public class MetaTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetaTrainingApplication.class, args);
    }

}
