package com.jmm.drools.vo;


import io.swagger.annotations.ApiModelProperty;

public class SubPromotionEdit {

	@ApiModelProperty(value = "活动名称",required = false)
	private String promotionName;
	
	@ApiModelProperty(value = "活动描述",required = false)
	private String promoitonDesc;
	
	@ApiModelProperty(value = "商品ID",required = false)
	private String productId;
	
	@ApiModelProperty(value = "商品名称",required = false)
	private String productName;
	
	@ApiModelProperty(value = "促销类型:1-按金额满减,2-按件满减,3-直降",required = false)
	private String proConType;
	
	@ApiModelProperty(value = "阈值",required = false)
	private String proThreshold;

	@ApiModelProperty(value = "原单价",required = true)
	private String oriPrice;
	
	@ApiModelProperty(value = "优惠金额",required = true)
	private String proPrice;
	
	@ApiModelProperty(value = "起始时间",required = false)
	private String startTime;
	
	@ApiModelProperty(value = "结束时间",required = false)
	private String endTime;

	@ApiModelProperty(value = "活动id",required = true)
	private String id;
	
	@ApiModelProperty(value = "子活动id",required = true)
	private String subid;
	@ApiModelProperty(value = "是否为秒杀活动")
	private String isSkill;
	@ApiModelProperty(value = "中收比")
	private String bankCharge;
	@ApiModelProperty(value = "活动标签")
	private String promotlabel;
	@ApiModelProperty(value = "活动库存")
	private String stock;
	@ApiModelProperty(value = "每人可购买件数")
	private String userlimit;

	public String getIsSkill() {
		return isSkill;
	}

	public void setIsSkill(String isSkill) {
		this.isSkill = isSkill;
	}

	public String getBankCharge() {
		return bankCharge;
	}

	public void setBankCharge(String bankCharge) {
		this.bankCharge = bankCharge;
	}

	public String getPromotlabel() {
		return promotlabel;
	}

	public void setPromotlabel(String promotlabel) {
		this.promotlabel = promotlabel;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getUserlimit() {
		return userlimit;
	}

	public void setUserlimit(String userlimit) {
		this.userlimit = userlimit;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getPromoitonDesc() {
		return promoitonDesc;
	}

	public void setPromoitonDesc(String promoitonDesc) {
		this.promoitonDesc = promoitonDesc;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProConType() {
		return proConType;
	}

	public void setProConType(String proConType) {
		this.proConType = proConType;
	}

	public String getProThreshold() {
		return proThreshold;
	}

	public void setProThreshold(String proThreshold) {
		this.proThreshold = proThreshold;
	}

	public String getOriPrice() {
		return oriPrice;
	}

	public void setOriPrice(String oriPrice) {
		this.oriPrice = oriPrice;
	}

	public String getProPrice() {
		return proPrice;
	}

	public void setProPrice(String proPrice) {
		this.proPrice = proPrice;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubid() {
		return subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}
}
