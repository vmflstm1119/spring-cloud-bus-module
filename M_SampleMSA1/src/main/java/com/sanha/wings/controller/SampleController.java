package com.sanha.wings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SampleController {

    private Environment environment;

    @Autowired
    public SampleController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/healthCheck")
    public String status() {
        return String.format("It's working in service"
                +", port(local.server.port)=" + environment.getProperty("local.server.port")
                +", port(server.port)=" + environment.getProperty("server.port")
                +", token secret=" + environment.getProperty("token.secret")
                +", token expiration time=" + environment.getProperty("token.expiration_time")
        );
    };
}
