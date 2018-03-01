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
public interface PersonStockDao {

	   
	  
	    
	    @Update("UPDATE csh_person_stock SET restStock = restStock-#{count} WHERE subPromotionId = #{subPromotionId} AND userId = #{userId} AND restStock-#{count}>=0")
	    Integer updatePersonStock(@Param("count") Integer count,@Param("subPromotionId") int subPromotionId,@Param("userId") long userId);
	    
	    
		@Insert("INSERT INTO csh_person_stock(id,subPromotionId,restStock,userId) VALUE"
				+ " (#{id},#{subPromotionId},#{restStock},#{userId})")
		Integer insertPersonStock(@Param("id") long id,
								@Param("subPromotionId") int subPromotionId,
								@Param("restStock") int restStock,
								@Param("userId") long userId
		);

	   
	    
}
