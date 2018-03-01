package com.jmm.drools.vo;

import com.jmm.drools.bean.ApproveSubPromotion;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="ApprovePromotionVo")
public class ApprovePromotionVo {


	@ApiModelProperty(value="银行活动审批")
	private ApproveSubPromotion subPromotion;

	public ApproveSubPromotion getSubPromotion() {
		return subPromotion;
	}

	public void setSubPromotion(ApproveSubPromotion subPromotion) {
		this.subPromotion = subPromotion;
	}
}
