package com.jmm.drools.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Description:供应商库存dao
 * @Author: xumengyang
 * @Date: Created in 12:55 2018/1/3
 */
@Mapper
public interface SupplierInventoryDao {
    /**
     * 查库存
     * @param proCoding
     * @return
     */
    @Select("select number from csh.csh_supplier_inventory where pro_Coding = #{proCoding}")
    Integer getMumberByProCoding(@Param("proCoding")String proCoding );

    /**
     * 根据proCoding减库存（活动预留库存）
     * @param proCoding
     * @param stock
     * @return
     */
    @Update("update csh.csh_supplier_inventory set number = number-#{stock} where f1 = #{bankId} and pro_Coding = #{proCoding} and number-#{stock}>=0")
    Integer updateNumberByProCoding(@Param("bankId")String bankId,@Param("stock")int stock, @Param("proCoding")String proCoding);

    @Update("update csh.csh_supplier_inventory a,csh.csh_sub_promotion b set a.number = a.number + b.reststockcount,b.reststockcount=0 where b.id=#{id} and a.f1=#{bankId} and a.pro_Coding =#{proCoding}")
    Integer backStockByProCoding(@Param("id") String subId,@Param("bankId")String bankId,@Param("proCoding")String proCoding);
}
