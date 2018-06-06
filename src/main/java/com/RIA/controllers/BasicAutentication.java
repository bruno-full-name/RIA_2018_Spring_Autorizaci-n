package com.RIA.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class BasicAutentication {

    @RequestMapping(method = RequestMethod.GET)
    public String echo() {
        return "Usuario y contraseña correctos :D";
    }

}
