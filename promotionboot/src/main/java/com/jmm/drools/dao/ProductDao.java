package com.jmm.drools.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;

/**
 * @Description:商品dao
 * @Author: xumengyang
 * @Date: Created in 14:09 2018/1/3
 */
@Mapper
public interface ProductDao {
    /**
     * 根据商品id获取pro_Coding
     * @param productId
     * @return
     */
    @Select("select pro_Coding from csh.csh_product where id = #{productId}")
    String getProCodingByProId(@Param("productId") String productId);

}
