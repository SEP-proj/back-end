package com.septeam.metatraining.common.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private Integer status;
    private String message;
    private T data;

    public ApiResponse (int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
