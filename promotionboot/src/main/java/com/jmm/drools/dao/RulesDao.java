package com.jmm.drools.dao;


import com.jmm.drools.bean.Rules;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RulesDao {

    @Select("SELECT * FROM csh_drools_rule where id = #{id}")
    Rules getById(@Param("id") Integer id);
    
    @Select("SELECT * FROM csh_drools_rule where name = #{name}")
    Rules getByName(@Param("name") String name);

    @Insert("INSERT INTO csh_drools_rule(name,rule) VALUE(#{name},#{rule})")
    Integer setRule(@Param("name") String name,@Param("rule") String rule);

    @Select("select max(id) id from csh_drools_rule")
    Integer getLastId();

    @Select("SELECT * FROM csh_drools_rule order by create_time DESC")
    List<Rules> getRuleList();

    @Update("UPDATE csh_drools_rule SET visible=0 WHERE id = #{id}")
    Integer deleteRule(@Param("id") Integer id);

    @Update("UPDATE csh_drools_rule SET rule= #{rule} AND name = #{name} WHERE id = #{id}")
    Integer updateRule(@Param("id") Integer id,@Param("name") String name,@Param("rule") String rule);
}
