package com.jmm.drools.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 促销子活动
 * @author biris
 *
 */
@ApiModel(value="SubPromotion")
public class SubPromotion implements Serializable{
	
	private static final long serialVersionUID = -5900563199884529443L;
	private int id;
	@ApiModelProperty(value = "活动名称",required = true)
	private String promotionName;
	@ApiModelProperty(value = "银行id",required = true)
	private String bankId;
	@ApiModelProperty(value = "商品id",required = true)
	private String productId;
	@ApiModelProperty(value = "商品Coding")
	private String proCoding;
	@ApiModelProperty(value = "商品单价",required = true)
	private int oriPrice;
	@ApiModelProperty(value = "审批状态",required = false)
	private String approveStatus;
	@ApiModelProperty(value = "规则id",required = false)
	private int ruleId;
	@ApiModelProperty(value = "活动id",required = false)
	private int promotionId;
	@ApiModelProperty(value = "商品名称",required = false)
	private String productName;
	@ApiModelProperty(value = "银行名称",required = false)
	private String bankName;
	//xumy
	@ApiModelProperty(value = "活动库存")
	private int stockcount;
	@ApiModelProperty(value = "标签")
	private String label;
	@ApiModelProperty(value = "每个用户限购件数")
	private int userlimit;
	@ApiModelProperty(value = "中收比例")
	private String put_bankCharge;
	
	@ApiModelProperty(value = "剩余库存")
	private int reststockcount;

	public String getProCoding() {
		return proCoding;
	}

	public void setProCoding(String proCoding) {
		this.proCoding = proCoding;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getStockcount() {
		return stockcount;
	}

	public void setStockcount(int stockcount) {
		this.stockcount = stockcount;
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

	@ApiModelProperty(value = "删除标记 0-可用,1-不可用",required = false)
	private String enable;
	
	@ApiModelProperty(value = "是否秒杀型直降",required = true)
	private int isSkill;
	
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
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
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
	
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	
	public int getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
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
	
	
	   @Override
	    public String toString() {
	        return "SubPromotion{" +
	                ", promotionName='" + this.promotionName + '\'' +
	                ", productName='" + this.productName + '\'' +
	                ", bankName='" + this.bankName + '\'' +
	                '}';
	    }
	   
	   
	   @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        SubPromotion subPromotion = (SubPromotion) o;
	        return this.productId.equals(subPromotion.getProductId())&&this.bankId.equals(subPromotion.getBankId()); 
	    }

	    @Override
	    public int hashCode() {
	        int result = String.valueOf(this.productId).hashCode();
	        result = 31 * result + this.bankId.hashCode();
	        return result;
	    }
		public String getEnable() {
			return enable;
		}
		public void setEnable(String enable) {
			this.enable = enable;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public int getIsSkill() {
			return isSkill;
		}
		public void setIsSkill(int isSkill) {
			this.isSkill = isSkill;
		}

		public int getReststockcount() {
			return reststockcount;
		}

		public void setReststockcount(int reststockcount) {
			this.reststockcount = reststockcount;
		}

	    
		
	    
}

















