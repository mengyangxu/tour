package com.xmy.portal.controller;


import com.xmy.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by 杨波 on 2017/5/28.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+"---"+password);
        String logg = userService.login();
        System.out.println("logg:"+logg);
        return "index";
    }

    @RequestMapping("/register")
    public String register(){
        return "index";
    }


}
