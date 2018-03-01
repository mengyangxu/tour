package com.jmm.drools.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.undertow.util.DateUtils;

/**
 * 促销规则
 * @author biris
 *
 */
@ApiModel(value="Promotion")
public class Promotion implements Serializable{

	private static final long serialVersionUID = 1255080982951891289L;

	private int id;

	@ApiModelProperty(value = "活动名称",required = true)
	private String promotionName;
	@ApiModelProperty(value = "审批状态:W-待审批,P-部分通过,A-审批通过,R-驳回",required = true)
	private String approveStatus;
	@ApiModelProperty(value = "活动状态:R-正常,T-中止",required = true)
	private String status;
	@ApiModelProperty(value = "创建时间",required = true)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "起始时间",required = true)
	private Date startTime;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "结束时间",required = true)
	private Date endTime;
	@ApiModelProperty(value = "活动描述",required = false)
	private String desc;
	@ApiModelProperty(value = "促销类型:1-按金额,2-按件数",required = true)	
	private int proConType;
	@ApiModelProperty(value = "促销阈值",required = true)
	private int proThreshold;
	@ApiModelProperty(value = "优惠金额",required = true)
	private int proPrice;
	@ApiModelProperty(value = "产品图片")
	private String proPic;
	@ApiModelProperty(value = "规则id")
	private int ruleId;

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getProPic() {
		return proPic;
	}

	public void setProPic(String proPic) {
		this.proPic = proPic;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
}
