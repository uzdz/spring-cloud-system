package com.alibaba.membercoinnacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MemberCoinNacosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberCoinNacosApplication.class, args);
	}

}
