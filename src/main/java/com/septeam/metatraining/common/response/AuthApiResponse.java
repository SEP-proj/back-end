package com.septeam.metatraining.common.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AuthApiResponse {
    public int status;
    public String message;

    public LocalDateTime timestamp = LocalDateTime.now();

    public AuthApiResponse() {}

    public AuthApiResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public AuthApiResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public AuthApiResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public AuthApiResponse setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
