package com.jmm.drools.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author biris
 *
 */
@ApiModel(value="PromotionInfoByUserId")
public class PromotionInfoByUserId {
	
	@ApiModelProperty(value = "子活动Id")
	private int subId;
	@ApiModelProperty(value = "子活动名称")
	private String subName;
	@ApiModelProperty(value = "银行id")
	private String bankId;
	@ApiModelProperty(value = "商品id")
	private String productId;
	@ApiModelProperty(value = "原始价格")
	private int oriPrice;
	@ApiModelProperty(value = "子活动审批状态")
	private String subAprStatus;
	@ApiModelProperty(value = "规则id")
	private int ruleId;
	@ApiModelProperty(value = "活动id")
	private int id;
	@ApiModelProperty(value = "活动名称")
	private String promotionName;
	@ApiModelProperty(value = "活动审批状态")
	private String approveStatus;
	@ApiModelProperty(value = "活动审批状态")
	private String pstatus;
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@ApiModelProperty(value = "起始时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@ApiModelProperty(value = "结束时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	@ApiModelProperty(value = "活动描述")
	private String pdesc;

	@ApiModelProperty(value = "促销类型")
	private int proConType;
	@ApiModelProperty(value = "促销阈值")
	private int proThreshold;
	@ApiModelProperty(value = "优惠金额")
	private int proPrice;
	@ApiModelProperty(value = "活动审批描述")
	private String approveDesc;
	@ApiModelProperty(value = "商品名称")
	private String productName;
	@ApiModelProperty(value = "银行名称")
	private String bankName;
	
	@ApiModelProperty(value = "是否秒杀型直降")
	private String isSkill;
	
	@ApiModelProperty(value = "活动初始库存")
	private int stockcount;
	
	@ApiModelProperty(value = "标签名称")
	private String label;
	
	@ApiModelProperty(value = "每人限制购买数量")
	private int userlimit;
	
	@ApiModelProperty(value = "中收")
	private String put_bankCharge;

	@ApiModelProperty(value = "图片")
	private String proPic;
	
	private int reststockcount;
	
	private String restStock;

	public String getProPic() {
		return proPic;
	}

	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
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
	public String getSubAprStatus() {
		return subAprStatus;
	}
	public void setSubAprStatus(String subAprStatus) {
		this.subAprStatus = subAprStatus;
	}
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
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
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
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
	public String getIsSkill() {
		return isSkill;
	}
	public void setIsSkill(String isSkill) {
		this.isSkill = isSkill;
	}
	public int getStockcount() {
		return stockcount;
	}
	public void setStockcount(int stockcount) {
		this.stockcount = stockcount;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getUserlimit() {
		return userlimit;
	}
	public void setUserlimit(int userlimit) {
		this.userlimit = userlimit;
	}
	public String getPut_bankCharge() {
		return put_bankCharge;
	}
	public void setPut_bankCharge(String put_bankCharge) {
		this.put_bankCharge = put_bankCharge;
	}

	public int getReststockcount() {
		return reststockcount;
	}

	public void setReststockcount(int reststockcount) {
		this.reststockcount = reststockcount;
	}

	public String getRestStock() {
		return restStock;
	}

	public void setRestStock(String restStock) {
		this.restStock = restStock;
	}

	
}
