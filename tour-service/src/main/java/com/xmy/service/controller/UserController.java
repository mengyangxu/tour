package com.xmy.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: xumengyang
 * @Date: Created in 16:04 2018/2/27
 */
@RestController
public class UserController {

    @RequestMapping("/login")
    public String login(){
        return "登录成功";
    }

}
