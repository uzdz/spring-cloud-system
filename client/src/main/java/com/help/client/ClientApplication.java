package com.help.client;

import com.help.client.config.ExcludeFromComponentScan;
import com.help.client.config.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "microservice-provider-user", configuration = MyRule.class)
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type=FilterType.ANNOTATION,value=ExcludeFromComponentScan.class)})
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
