package com.wos.dao.mapper;

import com.wos.pojo.InstallDetail;

public interface InstallDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.itsm_service_InstallDetail
     *
     * @mbggenerated Fri Dec 05 17:20:59 CST 2014
     */
    int deleteByPrimaryKey(String cguid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.itsm_service_InstallDetail
     *
     * @mbggenerated Fri Dec 05 17:20:59 CST 2014
     */
    int insert(InstallDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.itsm_service_InstallDetail
     *
     * @mbggenerated Fri Dec 05 17:20:59 CST 2014
     */
    int insertSelective(InstallDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.itsm_service_InstallDetail
     *
     * @mbggenerated Fri Dec 05 17:20:59 CST 2014
     */
    InstallDetail selectByPrimaryKey(String cguid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.itsm_service_InstallDetail
     *
     * @mbggenerated Fri Dec 05 17:20:59 CST 2014
     */
    int updateByPrimaryKeySelective(InstallDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.itsm_service_InstallDetail
     *
     * @mbggenerated Fri Dec 05 17:20:59 CST 2014
     */
    int updateByPrimaryKey(InstallDetail record);
}