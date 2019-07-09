package com.uzdz.user.controller;

import com.uzdz.user.clients.CommonClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${author.name}")
    private String authorName;

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
