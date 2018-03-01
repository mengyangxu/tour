package com.jmm.drools.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;

@ApiModel(value="Order")
public class Order implements Serializable{

	private static final long serialVersionUID = -371804997392127423L;
	private String id;
	 private String ordersId;
	 private long ordersprice;
	 private long orderspayprice;
	 private String bankId;
	 private int promotion;

	private OrderItem orderItem;
	
	 public OrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	public long getOrdersprice() {
		return ordersprice;
	}
	public void setOrdersprice(long ordersprice) {
		this.ordersprice = ordersprice;
	}

	public long getOrderspayprice() {
		return orderspayprice;
	}
	public void setOrderspayprice(long orderspayprice) {
		this.orderspayprice = orderspayprice;
	}

	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public int getPromotion() {
		return promotion;
	}
	public void setPromotion(int promotion) {
		this.promotion = promotion;
	}
}
