package com.jmm.drools.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ConditionVo")
public class ConditionVo {

	@ApiModelProperty(value = "活动名称")
	private String promotionName;

	//xumy
	@ApiModelProperty(value = "促销类型")
	private String proConType;

	@ApiModelProperty(value = "子活动状态")
	private String sastatus;

	public String getSastatus() {
		return sastatus;
	}

	public void setSastatus(String sastatus) {
		this.sastatus = sastatus;
	}

	public String getProConType() {
		return proConType;
	}

	public void setProConType(String proConType) {
		this.proConType = proConType;
	}

	@ApiModelProperty(value = "商品id")
	private String productId;
	@ApiModelProperty(value = "银行id")
	private String bankId;
	@ApiModelProperty(value = "起始时间")
	private String startTime;
	@ApiModelProperty(value = "结束时间")
	private String endTime;
	
	private int currentResult;
	
	private int pageSize;
	
	
	public int getCurrentResult() {
		return currentResult;
	}
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
