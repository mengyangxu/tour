package com.jmm.drools.bean;

import java.io.Serializable;
import java.util.Date;

public class OrderItem implements Serializable{

	private static final long serialVersionUID = -7980001184781855289L;
	private String ordersId;
	private String productid;
	private long quantity;
	private long orderitemsprice;
	private long orderitemspayprice;

	public String getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getOrderitemsprice() {
		return orderitemsprice;
	}
	public void setOrderitemsprice(long orderitemsprice) {
		this.orderitemsprice = orderitemsprice;
	}

	public long getOrderitemspayprice() {
		return orderitemspayprice;
	}
	public void setOrderitemspayprice(long orderitemspayprice) {
		this.orderitemspayprice = orderitemspayprice;
	}
}
