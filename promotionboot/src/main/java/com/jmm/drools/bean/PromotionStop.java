package com.jmm.drools.bean;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(value="PromotionStop")
public class PromotionStop implements Serializable {
	private String subId;
	private String bankId;
	private String proCoding;

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getProCoding() {
		return proCoding;
	}

	public void setProCoding(String proCoding) {
		this.proCoding = proCoding;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}
	
}
