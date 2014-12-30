package com.wos.pojo;

import java.util.Date;

public class EnterpriseContacts {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_base_EnterpriseContacts.cGUID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cguid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_base_EnterpriseContacts.cEnterpriseID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String centerpriseid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_base_EnterpriseContacts.cContactID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String ccontactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_base_EnterpriseContacts.cJob
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cjob;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_base_EnterpriseContacts.cIsOnJob
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Integer cisonjob;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_base_EnterpriseContacts.cCreater
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String ccreater;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_base_EnterpriseContacts.cCreateDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Date ccreatedate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_base_EnterpriseContacts.cUpdater
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cupdater;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_base_EnterpriseContacts.cUpdateDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Date cupdatedate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_base_EnterpriseContacts.cTimeStamp
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String ctimestamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_base_EnterpriseContacts.isNew
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Short isnew;
    
    //child element
    private ContactInfo contactInfo;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_base_EnterpriseContacts.cGUID
     *
     * @return the value of dbo.itsm_base_EnterpriseContacts.cGUID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCguid() {
        return cguid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_base_EnterpriseContacts.cGUID
     *
     * @param cguid the value for dbo.itsm_base_EnterpriseContacts.cGUID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCguid(String cguid) {
        this.cguid = cguid == null ? null : cguid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_base_EnterpriseContacts.cEnterpriseID
     *
     * @return the value of dbo.itsm_base_EnterpriseContacts.cEnterpriseID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCenterpriseid() {
        return centerpriseid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_base_EnterpriseContacts.cEnterpriseID
     *
     * @param centerpriseid the value for dbo.itsm_base_EnterpriseContacts.cEnterpriseID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCenterpriseid(String centerpriseid) {
        this.centerpriseid = centerpriseid == null ? null : centerpriseid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_base_EnterpriseContacts.cContactID
     *
     * @return the value of dbo.itsm_base_EnterpriseContacts.cContactID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCcontactid() {
        return ccontactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_base_EnterpriseContacts.cContactID
     *
     * @param ccontactid the value for dbo.itsm_base_EnterpriseContacts.cContactID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCcontactid(String ccontactid) {
        this.ccontactid = ccontactid == null ? null : ccontactid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_base_EnterpriseContacts.cJob
     *
     * @return the value of dbo.itsm_base_EnterpriseContacts.cJob
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCjob() {
        return cjob;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_base_EnterpriseContacts.cJob
     *
     * @param cjob the value for dbo.itsm_base_EnterpriseContacts.cJob
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCjob(String cjob) {
        this.cjob = cjob == null ? null : cjob.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_base_EnterpriseContacts.cIsOnJob
     *
     * @return the value of dbo.itsm_base_EnterpriseContacts.cIsOnJob
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Integer getCisonjob() {
        return cisonjob;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_base_EnterpriseContacts.cIsOnJob
     *
     * @param cisonjob the value for dbo.itsm_base_EnterpriseContacts.cIsOnJob
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCisonjob(Integer cisonjob) {
        this.cisonjob = cisonjob;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_base_EnterpriseContacts.cCreater
     *
     * @return the value of dbo.itsm_base_EnterpriseContacts.cCreater
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCcreater() {
        return ccreater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_base_EnterpriseContacts.cCreater
     *
     * @param ccreater the value for dbo.itsm_base_EnterpriseContacts.cCreater
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCcreater(String ccreater) {
        this.ccreater = ccreater == null ? null : ccreater.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_base_EnterpriseContacts.cCreateDate
     *
     * @return the value of dbo.itsm_base_EnterpriseContacts.cCreateDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Date getCcreatedate() {
        return ccreatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_base_EnterpriseContacts.cCreateDate
     *
     * @param ccreatedate the value for dbo.itsm_base_EnterpriseContacts.cCreateDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCcreatedate(Date ccreatedate) {
        this.ccreatedate = ccreatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_base_EnterpriseContacts.cUpdater
     *
     * @return the value of dbo.itsm_base_EnterpriseContacts.cUpdater
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCupdater() {
        return cupdater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_base_EnterpriseContacts.cUpdater
     *
     * @param cupdater the value for dbo.itsm_base_EnterpriseContacts.cUpdater
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCupdater(String cupdater) {
        this.cupdater = cupdater == null ? null : cupdater.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_base_EnterpriseContacts.cUpdateDate
     *
     * @return the value of dbo.itsm_base_EnterpriseContacts.cUpdateDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Date getCupdatedate() {
        return cupdatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_base_EnterpriseContacts.cUpdateDate
     *
     * @param cupdatedate the value for dbo.itsm_base_EnterpriseContacts.cUpdateDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCupdatedate(Date cupdatedate) {
        this.cupdatedate = cupdatedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_base_EnterpriseContacts.cTimeStamp
     *
     * @return the value of dbo.itsm_base_EnterpriseContacts.cTimeStamp
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCtimestamp() {
        return ctimestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_base_EnterpriseContacts.cTimeStamp
     *
     * @param ctimestamp the value for dbo.itsm_base_EnterpriseContacts.cTimeStamp
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCtimestamp(String ctimestamp) {
        this.ctimestamp = ctimestamp == null ? null : ctimestamp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_base_EnterpriseContacts.isNew
     *
     * @return the value of dbo.itsm_base_EnterpriseContacts.isNew
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Short getIsnew() {
        return isnew;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_base_EnterpriseContacts.isNew
     *
     * @param isnew the value for dbo.itsm_base_EnterpriseContacts.isNew
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setIsnew(Short isnew) {
        this.isnew = isnew;
    }

    public ContactInfo getContactInfo()
    {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo)
    {
        this.contactInfo = contactInfo;
    }
    
    
}