package com.jmm.drools.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.jmm.drools.bean.Promotion;
import com.jmm.drools.bean.PromotionInfo;
import com.jmm.drools.bean.PromotionInfoByUserId;
import com.jmm.drools.bean.PromotionMgr;


@Mapper
public interface PromotionDao {

	   @Select("SELECT * FROM csh_promotion where id = #{id}")
	   Promotion getById(@Param("id") Integer id);
	   
	   @Select("select b.startTime,b.endTime FROM csh.csh_sub_promotion a LEFT JOIN csh.csh_promotion b ON a.promotionId = b.id WHERE a.id=#{subId}  ")
	    PromotionInfo getPromotionIdTimeBySubId(@Param("subId") String subId);
	    
	    @Select("SELECT * FROM csh_promotion where promotionName = #{promotionName}")
	    Promotion getByName(@Param("promotionName") String promotionName);


	    @Insert("INSERT INTO csh_promotion(promotionName,approveStatus,pstatus,createTime,startTime,endTime,pdesc,proConType,proThreshold,proPrice,pro_pic) VALUE (#{promotionName},#{approveStatus},#{status},#{createTime},#{startTime},#{endTime},#{desc},#{proConType},#{proThreshold},#{proPrice},#{proPic})")
	    Integer setPromotion(@Param("promotionName") String promotionName,
	    		@Param("approveStatus") String approveStatus,
	    		@Param("status") String status,
	    		@Param("createTime") Date createTime,
	    		@Param("startTime") Date startTime,
	    		@Param("endTime") Date endTime,
	    		@Param("desc") String desc,
	    		@Param("proConType") int proConType,
	    		@Param("proThreshold") int proThreshold,
	    		@Param("proPrice") int proPrice,
				@Param("proPic") String proPic
	    		);

	    @Select("SELECT * FROM csh_promotion order by createTime DESC")
	    List<Promotion> getPromotionList();
	    
	   
	    @Select("SELECT a.id subId,a.bankId bankId,a.productId productId,a.oriPrice oriPrice,a.approveStatus subAprStatus,a.ruleId ruleId,b.id,b.promotionName,b.approveStatus,(case when now() between b.startTime and b.endTime then 'P' else 'T' END) as pstatus,b.createTime,b.startTime,b.endTime,b.pdesc,b.proConType,b.proThreshold,b.proPrice,b.approveDesc FROM csh.csh_sub_promotion a left join csh.csh_promotion b on a.promotionId = b.id where a.bankId=#{bankId}")
	    List<PromotionInfo> getPromotionInfoList(@Param("bankId") String bankId);

	   
	    
	    //xumy
		@Select("SELECT a.id subId,a.bankId bankId,a.productId productId,a.productName productName,a.bankName bankName,a.oriPrice oriPrice,a.approveStatus subAprStatus,a.approveDesc,a.ruleId ruleId,a.put_bankCharge put_bankCharge,a.isSkill,a.stockcount,a.reststockcount,a.userlimit,a.label ,b.id,b.promotionName,b.approveStatus,(case when now() between b.startTime and b.endTime then 'P' else 'T' END) as pstatus,b.createTime,b.startTime,b.endTime,b.pdesc,b.proConType,b.proThreshold,b.proPrice,b.approveDesc,(case \n" +
				"when a.approvestatus = 'W' and now() < b.startTime then 'W' \n" +
				"when a.approvestatus = 'W' and now() >= b.startTime  then 'O' \n" +
				"when a.approvestatus = 'R' and now() >= b.startTime then 'O' \n" +
				"when a.approvestatus = 'R' and now() < b.startTime then 'R'\n" +
				"when a.approvestatus = 'A' and now() < b.startTime then 'S'\n" +
				"when a.approvestatus = 'A' and now() >= b.startTime and now() < b.endTime then 'P' \n" +
				"when a.approvestatus = 'A' and now() > b.endTime then 'F' \n" +
				"when a.approvestatus = 'T' then 'T' end) as showStatus FROM csh.csh_sub_promotion a left join csh.csh_promotion b on a.promotionId = b.id where a.bankId=#{bankId} and a.productId=#{productId} and a.promotionId=#{promotionId}")
	    //@Select("SELECT a.id subId,a.bankId bankId,a.productId productId,a.productName productName,a.bankName bankName,a.oriPrice oriPrice,a.approveStatus subAprStatus,a.approveDesc,a.ruleId ruleId,b.id,b.promotionName,b.approveStatus,(case when now() between b.startTime and b.endTime then 'P' else 'T' END) as pstatus,b.createTime,b.startTime,b.endTime,b.pdesc,b.proConType,b.proThreshold,b.proPrice,b.approveDesc FROM csh.csh_sub_promotion a left join csh.csh_promotion b on a.promotionId = b.id where a.bankId=#{bankId} and a.productId=#{productId} and a.promotionId=#{promotionId}")
	    PromotionInfo getPromotionIdInfo(@Param("bankId") String bankId,@Param("productId") String productId, @Param("promotionId") String promotionId);
	    
	    @Select("SELECT a.label,a.stockcount,a.isSkill,a.userlimit,a.id subId,a.bankId bankId,a.productId productId,a.productName productName,a.bankName bankName,a.oriPrice oriPrice,a.approveStatus,a.ruleId ruleId,b.id,b.promotionName,(case when now() between b.startTime and b.endTime then 'P' WHEN NOW() < b.startTime THEN 'W'  else 'T' END) as pstatus,b.createTime,b.startTime,b.endTime,b.pdesc,b.proConType,b.proThreshold,b.proPrice,b.approveDesc FROM csh.csh_sub_promotion a left join csh.csh_promotion b on a.promotionId = b.id where a.bankId=#{bankId} and a.productId=#{productId} and now() between b.startTime and b.endTime and a.approveStatus = 'A' and a.`enable`=0")
	    PromotionInfo getPromotionInfo(@Param("bankId") String bankId,@Param("productId") String productId);
	    
	    
	    @Select("SELECT a.reststockcount,a.label,a.stockcount,a.isSkill,a.userlimit,a.id subId,a.bankId bankId,a.productId productId,a.productName productName,a.bankName bankName,a.oriPrice oriPrice,a.approveStatus,a.ruleId ruleId,b.id,b.promotionName,(CASE WHEN NOW() BETWEEN b.startTime AND b.endTime THEN 'P' ELSE 'T' END) AS pstatus,b.createTime,b.startTime,b.endTime,b.pdesc,b.proConType,b.proThreshold,b.proPrice,b.approveDesc,cs.restStock FROM csh.csh_sub_promotion a LEFT JOIN csh.csh_promotion b ON a.promotionId = b.id LEFT JOIN csh_person_stock cs ON cs.`subPromotionId`=a.`id` AND cs.`userId`=#{userId} where a.bankId=#{bankId} and a.productId=#{productId} and now() between b.startTime and b.endTime and a.approveStatus = 'A' ")
	    PromotionInfoByUserId getPromotionInfoByUserId(@Param("bankId") String bankId,@Param("productId") String productId,@Param("userId") String userId);
	    
	    @Select("SELECT a.approveStatus,a.reststockcount,a.stockcount,a.userlimit,a.ruleId ruleId, b.startTime,b.endTime,cs.restStock,(case when now() between b.startTime and b.endTime then 'P' WHEN NOW() < b.startTime THEN 'W'  else 'T' END) as pstatus FROM csh.csh_sub_promotion a LEFT JOIN csh.csh_promotion b  ON a.promotionId = b.id LEFT JOIN csh_person_stock cs  ON cs.`subPromotionId` = a.`id` AND cs.`userId` = #{userId} where a.id=#{subId}  and a.approveStatus = 'A'  and a.enable=0")
	    PromotionInfoByUserId getPromotionInfoByUserIdForApp(@Param("subId") String subId,@Param("userId") String userId);
	    /**
	     * 微商城用
	     * @param prodIds
	     * @param bankId
	     * @return
	     */
	    @Select("SELECT a.productName,a.label,a.stockcount,a.isSkill,a.userlimit,a.id subId,a.bankId bankId,a.productId productId,a.oriPrice oriPrice,a.approveStatus approveStatus,a.ruleId ruleId,b.id,b.promotionName,(case when now() between b.startTime and b.endTime then 'P' WHEN NOW() < b.startTime THEN 'W'  else 'T' END) as pstatus,b.createTime,b.startTime,b.endTime,b.pdesc,b.proConType,b.proThreshold,b.proPrice,b.approveDesc FROM csh.csh_sub_promotion a left join csh.csh_promotion b on a.promotionId = b.id where a.productId in (${prodIds}) and a.bankId=#{bankId} and a.`enable`=0")	    
	    List<PromotionInfo> getMgrPromotionInfoList(@Param("prodIds") String prodIds,@Param("bankId") String bankId);

	    /**
	     * 活动中心
	     * @param bankId
	     * @return
	     */
	    @Select("SELECT a.pro_coding as proCoding ,a.reststockcount,a.productName,a.label,a.stockcount,a.isSkill,a.userlimit,a.id subId,a.bankId bankId,a.productId productId,a.oriPrice oriPrice,a.approveStatus approveStatus,a.ruleId ruleId,b.id,b.promotionName,(CASE WHEN NOW() BETWEEN b.startTime AND b.endTime THEN 'P' WHEN NOW() < b.startTime THEN 'W'  ELSE 'T' END) AS pstatus,b.createTime,b.startTime,b.endTime,b.pdesc,b.proConType,b.proThreshold,b.proPrice,b.approveDesc,b.pro_pic proPic FROM csh.csh_sub_promotion a LEFT JOIN csh.csh_promotion b ON a.promotionId = b.id WHERE  now()<b.endTime AND a.bankId=#{bankId} and a.`enable`=0 AND a.`approvestatus`='A' ")
	    List<PromotionInfo> getPromotionCenterInfoList(String bankId);
	    
	    /**
	     * 活动中心为了删除
	     * @param bankId
	     * @return
	     */
	    @Select("SELECT a.productId productId FROM csh.csh_sub_promotion a LEFT JOIN csh.csh_promotion b ON a.promotionId = b.id WHERE  now()<b.endTime AND a.bankId=#{bankId} and a.`enable`=0 AND a.`approvestatus`='A' ")
	    List<PromotionInfo> getPromotionInfoForRemoveList(@Param("bankId") String bankId);

	    @Update("UPDATE csh_promotion SET approveStatus = #{approveStatus} WHERE id = #{id}")
	    Integer updateApprove(@Param("id") Integer id,@Param("approveStatus") String approveStatus);
	    
	    @Update("UPDATE csh_promotion SET pstatus = (CASE WHEN startTime <= DATE_FORMAT(CURDATE(), 'MM-dd-yyyy') and endTime>=DATE_FORMAT(CURDATE(), 'MM-dd-yyyy')  and pstatus = 'T' then 'P' WHEN endTime <  DATE_FORMAT(CURDATE(), 'MM-dd-yyyy')  and pstatus = 'P' THEN 'T' END)")
	    Integer UpdatePromotionTriger();
	    
	    @Update("UPDATE csh_promotion SET pstatus='T' WHERE id = #{id}")
	    Integer temPromotion(@Param("id") Integer id);
	    
	    
	    @Update("UPDATE csh_promotion SET pstatus='P' WHERE id = #{id}")
	    Integer resetPromotions(@Param("id") Integer id);

	    //xumy
	    @SelectProvider(type=SqlProvider.class, method="selectPromotionList")
	    List<PromotionMgr> getQryPromotionList(@Param("promotionName") String promotionName,
	    		@Param("productId") String productId,
	    		@Param("bankId") String bankId,
	    		@Param("proConType") String proConType,
	    		@Param("startTime") String startTime,
	    		@Param("endTime") String endTime, 
	    		@Param("currentResult") int currentResult, 
	    		@Param("pageSize") int pageSize,
				@Param("showStatus") String showStatus);
	    
	    @SelectProvider(type=SqlProvider.class, method="selectPromotionListCount")
	    Integer selectPromotionListCount(@Param("promotionName") String promotionName,
	    		@Param("productId") String productId,
	    		@Param("bankId") String bankId,
	    		@Param("startTime") String startTime,
	    		@Param("endTime") String endTime,
				@Param("showStatus") String showStatus,
				@Param("proConType") String proConType);

	    @Update("update csh_sub_promotion set approvestatus = 'T', stopTime = #{stopTime} where id = #{subId}")
	    Integer stopPromotion(@Param("subId") String subId, @Param("stopTime") Date stopTime);

	    @Update("update csh_sub_promotion set reststockcount=0 where id = #{subId}")
		Integer cleanRestStock(@Param("subId") String subId);

	    @Update(" <script>"
	    		+" update csh_promotion set enable = 1 WHERE approvestatus IN ('W','R') AND id IN "
	    		+ "  <foreach item=\"item\" index=\"index\" collection=\"array\" open=\"(\" separator=\",\" close=\")\"> #{item} </foreach> "
	    		+ "</script>")
	    Integer deletePromotion(String[] array_IDS);

	    @Update(" <script>"
	    		+" update csh_sub_promotion set enable = 1 WHERE approvestatus IN ('W','R') AND promotionId IN "
	    		+ "  <foreach item=\"item\" index=\"index\" collection=\"array\" open=\"(\" separator=\",\" close=\")\"> #{item} </foreach> "
	    		+ "</script>")
		Integer deleteSubPromotion(String[] array_IDS);
	    
	    /**
	     * 
	     * @param promotionName
	     * @param productId
	     * @param bankId
	     * @param oriPrice
	     * @param subId
	     * @return
	     */
	    @UpdateProvider(type=SqlProvider.class, method="updateSubPromotion")
	    Integer editSubPromotion(@Param("promotionName") String promotionName,
	    		@Param("productId") String productId,
	    		@Param("productName") String productName,
	    		@Param("oriPrice") String oriPrice,
	    		@Param("subId") String subId,
				@Param("isSkill") String isSkill,
				@Param("bankCharge") String bankCharge,
				@Param("promotlabel") String promotlabel,
				@Param("stockcount") String stockcount,
				@Param("userlimit")	String userlimit);
	    
	    /**
	     * 
	     * @param promotionName
	     * @param startTime
	     * @param endTime
	     * @param pdesc
	     * @param proConType
	     * @param proThreshold
	     * @param proPrice
	     * @return
	     */
	    @UpdateProvider(type=SqlProvider.class, method="updatePromotion")
	    Integer editPromotion(@Param("promotionName") String promotionName,
	    		@Param("startTime") String startTime,
	    		@Param("endTime") String endTime,
	    		@Param("pdesc") String pdesc,
	    		@Param("proConType") String proConType,
	    		@Param("proThreshold") String proThreshold,
	    		@Param("proPrice") String proPrice,
	    		@Param("id") String id);
	    
	    
	    @Select("SELECT a.reststockcount,a.productName,a.label,a.stockcount,a.isSkill,a.userlimit,a.id subId,a.bankId bankId,a.productId productId,a.oriPrice oriPrice,a.approveStatus approveStatus,a.ruleId ruleId,b.id,b.promotionName,(CASE WHEN NOW() BETWEEN b.startTime AND b.endTime THEN 'P' WHEN NOW() < b.startTime THEN 'W'  ELSE 'T' END) AS pstatus,b.createTime,b.startTime,b.endTime,b.pdesc,b.proConType,b.proThreshold,b.proPrice,b.approveDesc FROM csh.csh_sub_promotion a LEFT JOIN csh.csh_promotion b ON a.promotionId = b.id WHERE a.isSkill=0 AND a.productId IN (${prodIds}) AND a.bankId=#{bankId} and a.`enable`=0 ")	    
	    List<PromotionInfo> getMgrPromotionInfoExceptSckillList(@Param("prodIds") String prodIds,@Param("bankId") String bankId);

	    @Select("SELECT a.reststockcount,a.productName,a.label,a.stockcount,a.isSkill,a.userlimit,a.id subId,a.bankId bankId,a.productId productId,a.oriPrice oriPrice,a.approveStatus approveStatus,a.ruleId ruleId,b.id,b.promotionName,(CASE WHEN NOW() BETWEEN b.startTime AND b.endTime THEN 'P' WHEN NOW() < b.startTime THEN 'W'  ELSE 'T' END) AS pstatus,b.createTime,b.startTime,b.endTime,b.pdesc,b.proConType,b.proThreshold,b.proPrice,b.approveDesc,b.pro_pic proPic FROM csh.csh_sub_promotion a LEFT JOIN csh.csh_promotion b ON a.promotionId = b.id WHERE a.isSkill=1 and now()<b.endTime AND a.bankId=#{bankId} and a.`enable`=0 AND a.`approvestatus`='A' ")
	    List<PromotionInfo> getMgrpromotionInfoSckillList(@Param("bankId") String bankId);

		
}
