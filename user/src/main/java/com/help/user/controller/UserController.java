package com.help.user.controller;

import com.help.user.clients.CommonClient;
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
}
