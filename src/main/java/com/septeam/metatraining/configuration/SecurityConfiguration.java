package com.septeam.metatraining.configuration;

import com.septeam.metatraining.common.filter.AuthenticationExceptionFilter;
import com.septeam.metatraining.common.filter.NullPointExceptionFilter;
import com.septeam.metatraining.common.filter.TokenAuthenticationFilter;
import com.septeam.metatraining.common.handler.CustomAccessDeniedHandler;
import com.septeam.metatraining.common.handler.OAuth2FailHandler;
import com.septeam.metatraining.common.handler.OAuth2SuccessHandler;
import com.septeam.metatraining.security.command.application.service.CustomOAuth2UserService;
import com.septeam.metatraining.security.command.application.service.CustomUserDetailService;
import com.septeam.metatraining.security.command.domain.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import com.septeam.metatraining.security.command.domain.service.CustomTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Arrays;


@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2FailHandler oAuth2FailHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final NullPointExceptionFilter nullPointExceptionFilter;

    @Autowired
    @Qualifier("RestAuthenticationEntryPoint")
    private AuthenticationEntryPoint authEntryPoint;

    @Autowired
    private CustomTokenService customTokenService;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;



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

    AuthenticationExceptionFilter authenticationExceptionFilter(HandlerExceptionResolver resolver) {
        return new AuthenticationExceptionFilter(resolver);
    }



    TokenAuthenticationFilter tokenAuthenticationFilter(CustomTokenService customTokenService,
                                                        CustomUserDetailService customUserDetailService) {
        return new TokenAuthenticationFilter(customTokenService, customUserDetailService);
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
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()


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
                .failureHandler(oAuth2FailHandler);
        http
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler);

        http
                .addFilterBefore(tokenAuthenticationFilter(customTokenService, customUserDetailService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationExceptionFilter(resolver), TokenAuthenticationFilter.class)
                .addFilterBefore(nullPointExceptionFilter, AuthenticationExceptionFilter.class);


        return http.build();
    }

}
