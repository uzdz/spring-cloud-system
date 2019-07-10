package com.uzdz.user.controller;

import com.uzdz.user.clients.CommonClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.uzdz.user.jpa.UserRepository;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.util.concurrent.ExecutionException;

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

    @Value("${author.name}")
    private String authorName;

    @Autowired(required = false)
    private RedisClient redisClient;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/testSuccess")
    public String success() {
        return "user success";
    }

    @GetMapping("/authorName")
    public String getAuthorName() {
        // 通过spring cloud config 获取作者名称

        ActiveSpan.info("this is shywalking info");

        ActiveSpan.tag("this is shywalking tag", "uzdz");

        System.out.printf("shywalking trace id is : " + TraceContext.traceId());

        return authorName;
    }

    @Trace
    @GetMapping("/getCommonSuccess")
    public String getCommonSuccess() {
        // 通过spring cloud config 获取作者名称

        ActiveSpan.info("this is shywalking common success info");

        ActiveSpan.tag("this is shywalking tag", "user -> common");

        System.out.println("shywalking trace id is : " + TraceContext.traceId());

        return commonClient.success();
    }

    @Trace
    @GetMapping("/getCommonError")
    public String getCommonError() {
        // 通过spring cloud config 获取作者名称

        ActiveSpan.info("this is shywalking common error info");

        ActiveSpan.tag("this is shywalking tag", "user -> common");

        System.out.println("shywalking trace id is : " + TraceContext.traceId());

        return commonClient.peerError();
    }

    @GetMapping("/redisInfo")
    public String getRedisInfo() throws ExecutionException, InterruptedException {

        if (redisClient == null) {
            return "failed start redis client";
        }

        RedisAsyncCommands<String, String> async = redisClient.connect().async();

        RedisFuture<String> info = async.info();

        return info.get();
    }

    @GetMapping("/getAllUsers")
    public Object getAllUsers() {

        return userRepository.findAll();
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
