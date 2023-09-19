package com.septeam.metatraining.configuration;

import com.septeam.metatraining.common.handler.OAuth2FailHandler;
import com.septeam.metatraining.common.handler.OAuth2SuccessHandler;
import com.septeam.metatraining.security.command.application.service.CustomOAuth2UserService;
import com.septeam.metatraining.security.command.domain.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2FailHandler oAuth2FailHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("HEAD", "POST", "GET", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    public HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository(){
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()

                .authorizeRequests()
                .antMatchers("/post", "/").permitAll() //permit request

                .antMatchers("/swagger", "/swagger-ui.html", "/swagger-ui/**",
                        "/api-docs", "/api-docs/**", "/v3/api-docs/**").permitAll() //permit swagger

                .antMatchers("/login/**","/auth/**").permitAll() //permit login
                .anyRequest()
                .authenticated()
                .and()

                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")

                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2SuccessHandler)
                .failureHandler(oAuth2FailHandler)


        ;
        return http.build();
    }

}
