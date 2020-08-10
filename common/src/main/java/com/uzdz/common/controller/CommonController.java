package com.uzdz.common.controller;

import com.uzdz.common.clients.UserClient;
import com.uzdz.common.jpa.JobRepository;
import com.uzdz.common.jpa.po.Job;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组件Controller
 * @author uzdz
 * @date: 2019/5/26 16:58
 * @since 0.1.0
 */
@RestController
public class CommonController {

    @Autowired(required = false)
    private UserClient userClient;

    @Autowired
    JobRepository jobRepository;

    @GetMapping("/testSuccess")
    public String success() {
        return "common success";
    }

    @GlobalTransactional(name = "updateJob")
    @GetMapping("/update")
    public String updateJob() {

        Job job = jobRepository.findById(1).get();
        job.setName("dev");
        jobRepository.save(job);
        return "success";
    }

    @GetMapping("/peerError")
    public String peerError() {
        throw new RuntimeException("手动测试异常!");
    }

    @GetMapping("/testUserSuccess")
    public String userSuccess() {
        return userClient.success();
    }
}
