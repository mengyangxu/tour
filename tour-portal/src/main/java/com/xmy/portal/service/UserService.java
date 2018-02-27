package com.xmy.portal.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: xumengyang
 * @Date: Created in 16:02 2018/2/27
 */
@FeignClient(value = "tour-service")
public interface UserService {
    @RequestMapping("/login")
    String login();
}
