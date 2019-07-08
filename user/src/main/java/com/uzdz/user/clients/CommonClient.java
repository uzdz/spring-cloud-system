package com.uzdz.user.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * common组件调用
 * @author uzdz
 * @date: 2019/5/26 16:55
 * @since 0.1.0
 */
@FeignClient(name = "common", fallback = CommonClient.CommonClientFallback.class)
public interface CommonClient {

    @GetMapping("/testSuccess")
    String success();

    @Component
    class CommonClientFallback implements CommonClient {

        @Override
        public String success() {
            return "failed";
        }
    }
}
