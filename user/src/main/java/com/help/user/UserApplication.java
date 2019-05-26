package com.help.user;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @SpringBootApplication
 * @EnableDiscoveryClient = @SpringCloudApplication
 * @EnableCircuitBreaker
 */
@EnableFeignClients
@SpringCloudApplication
@EnableHystrixDashboard
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
