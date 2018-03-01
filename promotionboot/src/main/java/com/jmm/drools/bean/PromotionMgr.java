package com.jmm.drools.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PromotionMgr")
public class PromotionMgr {

	@ApiModelProperty(value = "子活动Id")
	private int sid;
	@ApiModelProperty(value = "子活动名称")
	private String spName;
	@ApiModelProperty(value = "银行Id")
	private String bankId;
	@ApiModelProperty(value = "商品Id")
	private String productId;
	@ApiModelProperty(value = "原价")
	private int oriPrice;
	@ApiModelProperty(value = "子活动审批状态")
	private String sastatus;
	@ApiModelProperty(value = "子活动状态")
	private String showStatus;
	@ApiModelProperty(value = "规则id")
	private int ruleId;
	@ApiModelProperty(value = "活动id")
	private String promotionId;
	@ApiModelProperty(value = "子活动审批描述")
	private String saDesc;
	@ApiModelProperty(value = "活动名称")
	private String promotionName;
	@ApiModelProperty(value = "审批状态")
	private String approveStatus;
	@ApiModelProperty(value = "活动状态")
	private String pstatus;
	@ApiModelProperty(value = "活动起始时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@ApiModelProperty(value = "活动结束时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	@ApiModelProperty(value = "活动描述")
	private String pdesc;
	@ApiModelProperty(value = "促销类型1:按金额满减,2:案件满减,3:直降")
	private int proConType;
	@ApiModelProperty(value = "促销阈值")
	private int proThreshold;
	@ApiModelProperty(value = "优惠金额")
	private int proPrice;
	@ApiModelProperty(value = "审批描述")
	private String approveDesc;
	@ApiModelProperty(value = "商品名称")
	private String productName;
	@ApiModelProperty(value = "银行名称")
	private String bankName;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getOriPrice() {
		return oriPrice;
	}
	public void setOriPrice(int oriPrice) {
		this.oriPrice = oriPrice;
	}
	public String getSastatus() {
		return sastatus;
	}
	public void setSastatus(String sastatus) {
		this.sastatus = sastatus;
	}
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	public String getSaDesc() {
		return saDesc;
	}
	public void setSaDesc(String saDesc) {
		this.saDesc = saDesc;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
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
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
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
	public String getApproveDesc() {
		return approveDesc;
	}
	public void setApproveDesc(String approveDesc) {
		this.approveDesc = approveDesc;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
}
