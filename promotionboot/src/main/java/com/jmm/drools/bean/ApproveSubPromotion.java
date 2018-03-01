package com.jmm.drools.bean;

import io.swagger.annotations.ApiModelProperty;

public class ApproveSubPromotion {

	@ApiModelProperty(value = "子活动id",required = true)
	private int id;
	
	@ApiModelProperty(value = "活动id",required = true)
	private int promotionId;

	@ApiModelProperty(value = "审批状态",required = true)
	private String approveStatus;

	@ApiModelProperty(value = "审批描述",required = true)
	private String approveDesc;

	@ApiModelProperty(value="商品coding")
	private String proCoding;

	public String getProCoding() {
		return proCoding;
	}

	public void setProCoding(String proCoding) {
		this.proCoding = proCoding;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}
	
	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	
	public String getApproveDesc() {
		return approveDesc;
	}

	public void setApproveDesc(String approveDesc) {
		this.approveDesc = approveDesc;
	}
}
