package com.septeam.metatraining.security.command.application.controller;

import com.septeam.metatraining.common.response.ApiResponse;
import com.septeam.metatraining.common.response.AuthApiResponse;
import com.septeam.metatraining.security.Principal.UserPrincipal;
import com.septeam.metatraining.security.command.application.dto.AuthResponse;
import com.septeam.metatraining.security.command.application.dto.AuthResponseBody;
import com.septeam.metatraining.security.command.application.service.IssueTokenService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IssueTokenService issueTokenService;

    @Autowired
    public AuthController(IssueTokenService issueTokenService) {
        this.issueTokenService = issueTokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<?> issueToken (@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) {

        String accessToken =  bearerToken.substring(7);
        System.out.println("accessToken = " + accessToken);

        String issuedToken = issueTokenService.issueTokenByAccessToken(accessToken);

        AuthApiResponse apiResponse = new AuthApiResponse()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Issue new Access Token")
                .setTimestamp(LocalDateTime.now());


        AuthResponseBody authResponseBody = new AuthResponseBody()
                .setAccessToken(issuedToken);

        AuthResponse authResponse = new AuthResponse()
                .setApiResponse(apiResponse)
                .setBody(authResponseBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }



    @GetMapping
    public String test(@AuthenticationPrincipal UserPrincipal userPrincipal){

        System.out.println("userPrincipal = " + userPrincipal);
        Long memberId = userPrincipal.getId();

        return "하이";
    }
}
