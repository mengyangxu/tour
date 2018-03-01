package com.jmm.drools.service;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmm.drools.common.IdUtil;
import com.jmm.drools.dao.PersonStockDao;
import com.jmm.drools.dao.SubPromotionDao;

@Service
public class StockService {
	
	@Resource
	PersonStockDao personStockDao;
	
	@Resource
	SubPromotionDao subPromotionDao;
	
	
	
/*	@Resource
	StockPersonService stockPersonService;
	
	int count = 2;
	
	int count1 = 3;
	
	long subPromotionId = 1;
	
	long userId = 1;
	
	
	
	@Transactional
	public String deductingInventory(int userlimit,int count,long subId,long userId){
		int stockResult = subPromotionDao.updateStock(count, subId);
		if(stockResult==1){
			try {
				personStockDao.insertPersonStock(IdUtil.getId(),subPromotionId,count1, userId);
				
				stockPersonService.updatePersonStock1();
			} catch (DuplicateKeyException e) {
				stockPersonService.updatePersonStock1();
			}
		}else{
			//下单失败，库存不足
			return null;
		}
		return null;
	}
*/
	

	
	
	
	
	
}
