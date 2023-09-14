package com.septeam.metatraining.common.annotation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK !!"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
        @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
})
public @interface CustomCommonApiResponse {

}
