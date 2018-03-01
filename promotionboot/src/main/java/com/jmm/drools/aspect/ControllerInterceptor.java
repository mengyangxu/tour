package com.jmm.drools.aspect;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.google.gson.Gson;
import com.jmm.drools.common.IpUtils;

@Aspect  
@Component  
public class ControllerInterceptor {  
    
    private final static Logger logger = LogManager.getLogger(ControllerInterceptor.class);  
    protected long threshold = 1000L;  
  
  
	/** 
     * 定义拦截规则：拦截com.jmm.skill.controller包下面的所有类中，有@RequestMapping注解的方法。 
     */  
    @Pointcut("execution(* com.jmm.skill.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")  
    public void controllerMethodPointcut(){} 
    /** 
     * 拦截器具体实现 
     * @param pjp 
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。） 
     */  
    @Around("controllerMethodPointcut()") //指定拦截器规则
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable { 
    	
        long beginTime = System.currentTimeMillis();  
        MethodSignature signature = (MethodSignature) pjp.getSignature();  
        Method method = signature.getMethod(); //获取被拦截的方法  
        String methodName = method.getName(); //获取被拦截的方法名  
        
		Object result = null;
		TraceLog traceLog = new TraceLog(this.logger, methodName, this.threshold);
		try {
			traceLog.begin();
			result = pjp.proceed();  
		} catch (Exception appEx) {
			traceLog.setExType("A");
			throw appEx;
		} catch (Throwable t) {
			traceLog.setExType("T");
			throw t;
		} finally {
			getParamaterLog();
			traceLog.end();
			traceLog.log();			
		}
		return result;

    }  
    
    
    private void getParamaterLog(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String iRealIPAddr = IpUtils.getIpAddr(request);
        logger.info("request ip"+iRealIPAddr);
        Gson gson = new Gson();
        logger.info(gson.toJson(request.getParameterMap()));
      }
    
    public long getThreshold() {
  		return threshold;
  	}
  	public void setThreshold(long threshold) {
  		this.threshold = threshold;
  	}
      
   
} 

