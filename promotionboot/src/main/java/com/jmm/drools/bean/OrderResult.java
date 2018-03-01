package com.jmm.drools.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class OrderResult implements Serializable{
	
	private static final long serialVersionUID = 154491269568916847L;
	@ApiModelProperty(value = "活动名称",required = false)
	private String promotionName;
	@ApiModelProperty(value = "审批状态:W-待审批,P-部分通过,A-审批通过,R-驳回",required = false)
	private String approvalStatus;
	@ApiModelProperty(value = "活动状态:R-正常,T-中止",required = false)
	private String status;
	@ApiModelProperty(value = "创建时间",required = false)
	private Date createTime;
	@ApiModelProperty(value = "起始时间",required = false)
	private Date startTime;
	@ApiModelProperty(value = "结束时间",required = false)
	private Date endTime;
	@ApiModelProperty(value = "活动描述",required = false)
	private String desc;
	@ApiModelProperty(value = "促销类型:1-按金额,2-按件数",required = false)	
	private int proConType;
	@ApiModelProperty(value = "促销阈值",required = false)
	private int proThreshold;
	@ApiModelProperty(value = "优惠金额",required = false)
	private int proPrice;
	@ApiModelProperty(value = "订单id",required = false)
	private String ordersId;
	@ApiModelProperty(value = "订单价格",required = false)
	private long ordersprice;
	@ApiModelProperty(value = "订单支付价格",required = false)	
	private long orderspayprice;
	@ApiModelProperty(value = "银行id",required = false)
	private String bankId;
	@ApiModelProperty(value = "活动id",required = false)
	private int promotion;
	@ApiModelProperty(value = "银行中收",required = false)
	private float put_bankCharge;

	public float getPut_bankCharge() {
		return put_bankCharge;
	}
	public void setPut_bankCharge(float put_bankCharge) {
		this.put_bankCharge = put_bankCharge;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getProConType() {
		return proConType;
	}
	public void setProConType(int proConType) {
		this.proConType = proConType;
	}
	public int getProThreshold() {
		return proThreshold;
	}
	public void setProThreshold(int proThreshold) {
		this.proThreshold = proThreshold;
	}
	public int getProPrice() {
		return proPrice;
	}
	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
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
