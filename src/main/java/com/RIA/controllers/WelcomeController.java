package com.RIA.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController("index")
public class WelcomeController {

    @GetMapping
    public String welcome(HttpServletRequest request) {
        return "Bienvendio " + request.getAttribute("username");
    }

}
