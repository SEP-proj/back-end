package com.septeam.metatraining.security.command.application.controller;

import com.septeam.metatraining.security.command.domain.aggregate.util.CookieUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.septeam.metatraining.security.command.domain.repository.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@RestController
public class HomeController {


//    @GetMapping("/")
//    public void home(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);
//        System.out.println("redirectUri.get() = " + redirectUri.get());
//        response.sendRedirect(redirectUri.get());
//    }
}
