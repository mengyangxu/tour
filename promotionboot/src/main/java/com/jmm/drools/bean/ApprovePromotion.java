package com.jmm.drools.bean;

import io.swagger.annotations.ApiModelProperty;

public class ApprovePromotion {

	@ApiModelProperty(value = "活动id",required = true)
	private int id;
	
	@ApiModelProperty(value = "审批状态",required = false)
	private String approvalStatus;
	
	@ApiModelProperty(value = "活动状态",required = false)
	private String status;
	
	@ApiModelProperty(value = "审批描述",required = false)
	private String approveDesc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public String getApproveDesc() {
		return approveDesc;
	}

	public void setApproveDesc(String approveDesc) {
		this.approveDesc = approveDesc;
	}
}
