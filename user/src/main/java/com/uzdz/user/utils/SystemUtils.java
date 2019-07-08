package com.uzdz.user.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemUtils {

    Logger logger = LoggerFactory.getLogger(SystemUtils.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取服务器系统时间戳
     * @return
     */
    @GetMapping("/getSystemTimestamp")
    public String getSystemTimestamp() {
        String timeMillis = String.valueOf(System.currentTimeMillis());
        logger.info("获取当前系统时间"+ timeMillis);
        return timeMillis+"USER";
    }

    /**
     * 发现SpringCloud eureka 中的服务
     * @return
     */
    @RequestMapping("/discoveryServer")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        services.stream().forEach((x) -> System.out.println(x));
        List<ServiceInstance> common = discoveryClient.getInstances("common");
        common.stream().forEach((x) -> System.out.println(x.getServiceId()+"-"+x.getUri()));
        return this.discoveryClient;
    }
}
