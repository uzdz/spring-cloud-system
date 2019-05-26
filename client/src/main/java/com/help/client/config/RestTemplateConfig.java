package com.help.client.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /**
     * 添加负载均衡支持，很简单，只需要在RestTemplate上添加@LoadBalanced注解，
     * 那么RestTemplate即具有负载均衡的功能,
     * 如果不加@LoadBalanced注解的话，会报java.net.UnknownHostException:异常，
     * 此时无法通过注册到Eureka Server上的服务名来调用服务，
     * 因为RestTemplate是无法从服务名映射到ip:port的，映射的功能是由LoadBalancerClient来实现的。
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
