package com.help.client.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/getUser")
    public String getUser() {

        // 第一种方式（直接使用RestTemplate + Url写死调用）
        RestTemplate restTemplate = new RestTemplate();
        String msg1 = restTemplate.getForObject("http://localhost:8080/msg", String.class);

        // 第二种方式（利用LoadBalancerClient通过应用名和负载均衡策略获取该应用下的某个url，然后在使用RestTemplate调用）
        ServiceInstance instance = loadBalancerClient.choose("PUBLIC");
        String msg2 = restTemplate.getForObject("http://" + instance.getHost() + ":" + instance.getPort() + "/msg", String.class);

        // 第三种方式（利用Spring的Bean注入方式，将RestTemplate通过java的方式注入到容器中，并通过@LoadBalanced注解扩展RestTemplate功能，使其通过ServerId调用服务）
        String msg3 = this.restTemplate.getForObject("http://PUBLIC/msg", String.class);

        return msg3;
    }
}
