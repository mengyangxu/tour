package com.jmm.drools.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 促销规则
 * @author biris
 *
 */
@ApiModel(value="PromotionDelete")
public class PromotionDelete implements Serializable{


	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	

	
}
