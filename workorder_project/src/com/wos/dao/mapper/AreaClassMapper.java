package com.wos.dao.mapper;

import java.util.List;

import com.wos.pojo.AreaClass;

public interface AreaClassMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CM_AreaClass
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    int insert(AreaClass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CM_AreaClass
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    int insertSelective(AreaClass record);
    
    List<AreaClass> findAllAreaClasses();
}