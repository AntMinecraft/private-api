package com.antonio32a.privateapi.controller;

import com.antonio32a.privateapi.responses.HelloResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class HelloController {
    private final BuildProperties buildProperties;
    @Value("${ant.name}") private String name;

    public HelloController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @GetMapping({"/", "/hello"})
    public HelloResponse root() {
        return HelloResponse.builder()
            .name(name)
            .version(buildProperties.getVersion())
            .buildTime(buildProperties.getTime().getEpochSecond())
            .build();
    }
}
