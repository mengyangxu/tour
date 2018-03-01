package com.jmm.drools.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmm.drools.dao.PersonStockDao;
import com.jmm.drools.dao.SubPromotionDao;
import com.jmm.drools.utils.FastJsonUtils;
import com.jmm.drools.web.PromotionController;

@Service
public class StockPersonService {
	
	@Resource
	PersonStockDao personStockDao;
	
	@Resource
	SubPromotionDao subPromotionDao;
	
	private final static Logger logger = LoggerFactory.getLogger(StockPersonService.class); 

	public String updatePersonStock(int subPromotionId, int count, long userId,String ordersId) {
		Map<String, Object> resmap = new HashMap<>();
		int result = personStockDao.updatePersonStock(count, subPromotionId,userId);
		if(result==1){
			//返回下单成功
			logger.info("抢购成功"+userId+"orderId"+ordersId);
			resmap.put("code", "10000");
			resmap.put("message", "抢购成功");
			return FastJsonUtils.toJSONString(resmap);
		}else{
			subPromotionDao.updateStock(count, subPromotionId);
			logger.info("购买数量大于商品限制购买数量"+userId+"orderId"+ordersId);
			//返回下单失败
			resmap.put("code", "10613");
			resmap.put("message", "购买数量大于商品限制购买数量");
			return FastJsonUtils.toJSONString(resmap);
		}
		
	}

	
	
	
	
	
}
