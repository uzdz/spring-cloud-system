package com.uzdz.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Server7001Application {

    public static void main(String[] args) {
        SpringApplication.run(Server7001Application.class, args);
    }
}
