package com.alibaba.membernacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MemberNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberNacosApplication.class, args);
    }

}
