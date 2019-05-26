package com.help.user.controller;

import com.help.user.clients.CommonClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Controller
 * @author uzdz
 * @date: 2019/5/26 16:58
 * @since 0.1.0
 */
@RestController
public class UserController {

    @Autowired
    private CommonClient commonClient;

    @GetMapping("/testSuccess")
    public String success() {
        return commonClient.success();
    }

    @GetMapping("/failed")
    @HystrixCommand(fallbackMethod = "failedError")
    public String failed() {
        //return commonClient.success();
        throw new RuntimeException("手动抛出错误！");
    }

    public String failedError() {
        return "failed error";
    }
}
