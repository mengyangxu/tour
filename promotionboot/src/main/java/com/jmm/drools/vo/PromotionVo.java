package com.jmm.drools.vo;

import java.util.List;

import com.jmm.drools.bean.Promotion;
import com.jmm.drools.bean.SubPromotion;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="PromotionVo")
public class PromotionVo {

	@ApiModelProperty(value = "主活动")
	private Promotion promotion;
	
	@ApiModelProperty(value = "子活动")
	private List<SubPromotion> subPromotions;	
	
	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public List<SubPromotion> getSubPromotion() {
		return subPromotions;
	}

	public void setSubPromotion(List<SubPromotion> subPromotions) {
		this.subPromotions = subPromotions;
	}
}
