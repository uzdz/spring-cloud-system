package com.alibaba.membernacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MemberNacosController {

    @Value("${geek.name:unknown}")
    private String author;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/author")
    public String author() {
        return author;
    }
}
