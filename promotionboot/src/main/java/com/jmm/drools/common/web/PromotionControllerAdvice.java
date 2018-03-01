package com.jmm.drools.common.web;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.jmm.drools.common.exception.BusinessException;
import com.jmm.drools.common.exception.JsonResponse;
import net.sf.json.JSONObject;

@ControllerAdvice
public class PromotionControllerAdvice {

	public static final String DEFAULT_ERROR_VIEW = "error";
	 
	private static Logger logger = LogManager.getLogger();
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req,HttpServletResponse rps, Exception e) throws Exception {
	         if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
	             throw e;
	         
	 	     ModelAndView mav = new ModelAndView();
	         JSONObject result = new JSONObject();
	         if(e instanceof BusinessException)
	         {
	        	 result.put("code", ((BusinessException) e).getCode());
		         result.put("message", e.getMessage());
	         }
	         else
	         {
	        	 result.put("code", 9996);
	        	 result.put("message", "请求参数格式错误");
	         }
	         try {
	        	 rps.setHeader("Content-type", "application/json;charset=UTF-8");
	        	 rps.getWriter().write(result.toString());
	         } catch (IOException ioe) {
	             logger.error("json response error", ioe);
	             logger.error("error", e);
	         }
	         return mav;
	     }
}
