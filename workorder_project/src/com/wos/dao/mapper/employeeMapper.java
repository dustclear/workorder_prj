package com.wos.dao.mapper;

import com.wos.pojo.employee;

public interface employeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.cm_employee
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    int insert(employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.cm_employee
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    int insertSelective(employee record);
}