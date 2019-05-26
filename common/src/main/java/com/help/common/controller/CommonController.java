package com.help.common.controller;

import com.help.common.clients.UserClient;
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

    @Autowired
    private UserClient userClient;

    @GetMapping("/testSuccess")
    public String success() {
        return userClient.success();
    }
}
