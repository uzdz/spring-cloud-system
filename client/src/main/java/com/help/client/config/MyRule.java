package com.help.client.config;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ExcludeFromComponentScan
@Configuration
public class MyRule {

    @Bean
    public RandomRule myRule() {
        return new RandomRule();
    }
}
