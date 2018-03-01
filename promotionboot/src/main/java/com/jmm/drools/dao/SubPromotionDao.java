package com.jmm.drools.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jmm.drools.bean.PromotionInfo;
import com.jmm.drools.bean.SubPromotion;

@Mapper
public interface SubPromotionDao {

	   @Select("SELECT * FROM csh_sub_promotion where id = #{id} and enable=0")
	   SubPromotion getById(@Param("id") Integer id);
	   
	   
	   @Select("SELECT a.reststockcount,a.approveStatus approveStatus,(case when now() between b.startTime and b.endTime then 'P' WHEN NOW() < b.startTime THEN 'W'  else 'T' END) as pstatus,b.startTime,b.endTime FROM csh.csh_sub_promotion a left join csh.csh_promotion b on a.promotionId = b.id  where a.id = #{id} and a.enable=0")
	   PromotionInfo getInfoById(@Param("id") String id);
	   
	   
	   @Select("SELECT a.label,a.stockcount,a.isSkill,a.userlimit,a.id subId,a.bankId bankId,a.productId productId,a.oriPrice oriPrice,a.approveStatus approveStatus,a.ruleId ruleId,b.id,b.promotionName,(case when now() between b.startTime and b.endTime then 'P' WHEN NOW() < b.startTime THEN 'W'  else 'T' END) as pstatus,b.createTime,b.startTime,b.endTime,b.pdesc,b.proConType,b.proThreshold,b.proPrice,b.approveDesc FROM csh.csh_sub_promotion a left join csh.csh_promotion b on a.promotionId = b.id where a.productId in (${prodIds}) and a.bankId=#{bankId} and a.`enable`=0")	    
	    List<PromotionInfo> getMgrPromotionInfoList(@Param("prodIds") String prodIds,@Param("bankId") String bankId);

	   
	    
	    @Select("SELECT * FROM csh_sub_promotion where promotionName = #{promotionName} and enable=0")
	    SubPromotion getByName(@Param("promotionName") String promotionName);
	    
	    //@Select("SELECT a.id FROM csh_sub_promotion a LEFT JOIN csh_promotion b ON a.promotionId = b.id WHERE a.bankId = #{bankId} AND a.productId = #{productId} and a.enable = 0 AND a.approvestatus!='T' and NOW()<endtime ")
		@Select("SELECT a.id FROM csh_sub_promotion a LEFT JOIN csh_promotion b ON a.promotionId = b.id WHERE a.bankId = #{bankId} AND a.productId = #{productId} and a.enable = 0 AND ((a.approvestatus='W' and NOW()<startTime) or (a.approvestatus='A' and NOW()<endTime) or (a.approvestatus='R' and NOW()<startTime)) ")
	    SubPromotion getByDetail(@Param("bankId") String bankId,@Param("productId") String productId);
	    
	    @Select("SELECT id,userlimit  FROM csh_sub_promotion  where bankId=#{bankId}  and promotionId=#{promotionId} and enable = 0")
	    SubPromotion getSubPromotionId(@Param("bankId") String bankId, @Param("promotionId") Integer promotionId);
	    
	    
	    @Select("SELECT count(1) FROM csh_sub_promotion where promotionId = #{promotionId} and approveStatus in ('W','R') and enable = 0")
	    Integer noApprovedCount(@Param("promotionId") Integer promotionId);
	    
	    @Select("SELECT count(1) FROM csh_sub_promotion where promotionId = #{promotionId} and approveStatus = 'A' and enable = 0")
	    Integer approvedCount(@Param("promotionId") Integer promotionId);

	    /*@Insert("INSERT INTO csh_sub_promotion(promotionName,bankId,productId,oriPrice,approveStatus,promotionId,productName,bankName,enable) VALUE"
	    		+ " (#{promotionName},#{bankId},#{productId},#{oriPrice},#{approveStatus},#{promotionId},#{productName},#{bankName},1)")
	    Integer setSubPromotion(@Param("promotionName") String promotionName,
	    		@Param("bankId") String bankId,
	    		@Param("productId") String productId,
	    		@Param("oriPrice") int oriPrice,
	    		@Param("approveStatus") String approveStatus,
	    		@Param("promotionId") int promotionId,
	    		@Param("productName") String productName,
	    		@Param("bankName") String bankName
	    		);*/
	    //xumy
		@Insert("INSERT INTO csh_sub_promotion(promotionName,bankId,productId,oriPrice,approveStatus,promotionId,productName,bankName,enable,stockcount,label,userlimit,put_bankCharge,isSkill,reststockcount,pro_coding) VALUE"
				+ " (#{promotionName},#{bankId},#{productId},#{oriPrice},#{approveStatus},#{promotionId},#{productName},#{bankName},0,#{stockcount},#{label},#{userlimit},#{put_bankCharge},#{isSkill},#{stockcount},#{proCoding})")
		Integer setSubPromotion(@Param("promotionName") String promotionName,
								@Param("bankId") String bankId,
								@Param("productId") String productId,
								@Param("oriPrice") int oriPrice,
								@Param("approveStatus") String approveStatus,
								@Param("promotionId") int promotionId,
								@Param("productName") String productName,
								@Param("bankName") String bankName,
								@Param("stockcount") int stockcount,
								@Param("label") String label,
								@Param("userlimit") int userlimit,
								@Param("put_bankCharge") String put_bankCharge,
								@Param("isSkill") int isSkill,
                                @Param("proCoding") String proCoding
		);

	    @Select("SELECT * FROM csh_sub_promotion where enable =0 order by createTime DESC")
	    List<SubPromotion> getSubPromotionList();

	    @Update("UPDATE csh_sub_promotion SET approveStatus = #{approveStatus},approveDesc = #{approveDesc} WHERE id = #{id}")
	    Integer updateApprove(@Param("id") Integer id,@Param("approveStatus") String approveStatus,@Param("approveDesc") String approveDesc);
	    
		@Select("SELECT * FROM csh_sub_promotion where promotionId = #{promotionId} and enable = 0")
		List<SubPromotion> getByPromotionId(@Param("promotionId") Integer promotionId);
		
	    @Update("UPDATE csh_sub_promotion SET ruleId = #{ruleId} WHERE id = #{id}")
	    Integer updateRuleId(@Param("id") Integer id,@Param("ruleId") Integer ruleId);
	    
	    @Update("UPDATE csh_promotion SET ruleId = #{ruleId} WHERE id = #{id}")
	    Integer updatePromotionRuleId(@Param("id") Integer id,@Param("ruleId") Integer ruleId);
	    
	    @Select("SELECT * FROM csh_sub_promotion where enable =0 and bankId = #{bankId} and promotionId=#{promotionId}")
	    SubPromotion getSubPromotionForCharge(@Param("bankId") String bankId,@Param("promotionId") int promotionId);
	    
	    @Update("UPDATE csh_sub_promotion SET approveStatus = 'A' WHERE id = #{id}")
	    Integer updateApproveByJob(@Param("id") Integer id);
	    
	    @Update("UPDATE csh_sub_promotion SET reststockcount = reststockcount-#{count} WHERE id = #{id} AND reststockcount-#{count}>=0")
	    Integer updateStock(@Param("count") Integer count,@Param("id") long id);
	    
	    @Update("UPDATE csh_sub_promotion SET reststockcount = reststockcount+#{count} WHERE id = #{id} ")
	    Integer updateAddStock(@Param("count") Integer count,@Param("id") long id);
	    
}
