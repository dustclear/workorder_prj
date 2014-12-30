package com.wos.dao.mapper;

import java.util.List;

import com.wos.pojo.ContactInfo;
import com.wos.pojo.InstallDetail;

public interface ContactInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.itsm_base_Contact
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    int insert(ContactInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.itsm_base_Contact
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    int insertSelective(ContactInfo record);
    
    List<ContactInfo> findContactInfoByEnterpriseId(String enterpriseId);
    List<ContactInfo> findContactInfoById(String cguid);
    
    
    int updateContactPhone(ContactInfo record);
    
    int updateContactInfo(ContactInfo record);
    
    int deleteByPrimaryKey(String cguid);
    
}