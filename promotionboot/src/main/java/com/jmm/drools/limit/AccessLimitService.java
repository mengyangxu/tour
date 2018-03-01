package com.jmm.drools.limit;

import com.google.common.util.concurrent.RateLimiter;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Description:限流服务
 * 
 */
@Component("accessLimitService")
public class AccessLimitService {

    //每秒只发出1000个令牌
    RateLimiter rateLimiter = RateLimiter.create(1000);

    /**
     * 尝试获取令牌
     * @return
     */
    public boolean tryAcquire(){  
        return rateLimiter.tryAcquire();
    }
}
