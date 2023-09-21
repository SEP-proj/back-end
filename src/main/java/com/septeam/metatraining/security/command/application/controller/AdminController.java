package com.septeam.metatraining.security.command.application.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @PostMapping("/admin")
    public String test(){
        return "test";
    }
}
