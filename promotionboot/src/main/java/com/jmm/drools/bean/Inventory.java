package com.jmm.drools.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel(value="Inventory")
public class Inventory implements Serializable{

	 private String ordersId;
	 private String bankId;
	 private int promotionId;
	 
	 private int subPromotionId;
	 private long userid;
	 private int count;
	
	
	public String getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
 
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}
	public int getSubPromotionId() {
		return subPromotionId;
	}
	public void setSubPromotionId(int subPromotionId) {
		this.subPromotionId = subPromotionId;
	}
	
	
	
	
	
}
