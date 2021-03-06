package com.wos.pojo;

public class RmsUser {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.cGUID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cguid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.cNAME
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.cPWD
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cpwd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.cRealName
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String crealname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.cEMP
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cemp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.cOrgnID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String corgnid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.iENABLE
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Integer ienable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.cUserType
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cusertype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.cPwdType
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cpwdtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.dNameStartDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String dnamestartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.dNameEndDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String dnameenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.dPwdStartDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String dpwdstartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.dPwdEndDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String dpwdenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.iConcurrent
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Integer iconcurrent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.iFirstLogin
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Integer ifirstlogin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.cAuthType
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cauthtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.cIDENTITY
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cidentity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.cDESCP
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cdescp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.iIfLoginSys
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Integer iifloginsys;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.AOS_RMS_USER.iSystemUser
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Integer isystemuser;
    
    private Employee employee;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.cGUID
     *
     * @return the value of dbo.AOS_RMS_USER.cGUID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCguid() {
        return cguid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.cGUID
     *
     * @param cguid the value for dbo.AOS_RMS_USER.cGUID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCguid(String cguid) {
        this.cguid = cguid == null ? null : cguid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.cNAME
     *
     * @return the value of dbo.AOS_RMS_USER.cNAME
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCname() {
        return cname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.cNAME
     *
     * @param cname the value for dbo.AOS_RMS_USER.cNAME
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.cPWD
     *
     * @return the value of dbo.AOS_RMS_USER.cPWD
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCpwd() {
        return cpwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.cPWD
     *
     * @param cpwd the value for dbo.AOS_RMS_USER.cPWD
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCpwd(String cpwd) {
        this.cpwd = cpwd == null ? null : cpwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.cRealName
     *
     * @return the value of dbo.AOS_RMS_USER.cRealName
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCrealname() {
        return crealname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.cRealName
     *
     * @param crealname the value for dbo.AOS_RMS_USER.cRealName
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCrealname(String crealname) {
        this.crealname = crealname == null ? null : crealname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.cEMP
     *
     * @return the value of dbo.AOS_RMS_USER.cEMP
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCemp() {
        return cemp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.cEMP
     *
     * @param cemp the value for dbo.AOS_RMS_USER.cEMP
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCemp(String cemp) {
        this.cemp = cemp == null ? null : cemp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.cOrgnID
     *
     * @return the value of dbo.AOS_RMS_USER.cOrgnID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCorgnid() {
        return corgnid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.cOrgnID
     *
     * @param corgnid the value for dbo.AOS_RMS_USER.cOrgnID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCorgnid(String corgnid) {
        this.corgnid = corgnid == null ? null : corgnid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.iENABLE
     *
     * @return the value of dbo.AOS_RMS_USER.iENABLE
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Integer getIenable() {
        return ienable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.iENABLE
     *
     * @param ienable the value for dbo.AOS_RMS_USER.iENABLE
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setIenable(Integer ienable) {
        this.ienable = ienable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.cUserType
     *
     * @return the value of dbo.AOS_RMS_USER.cUserType
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCusertype() {
        return cusertype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.cUserType
     *
     * @param cusertype the value for dbo.AOS_RMS_USER.cUserType
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCusertype(String cusertype) {
        this.cusertype = cusertype == null ? null : cusertype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.cPwdType
     *
     * @return the value of dbo.AOS_RMS_USER.cPwdType
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCpwdtype() {
        return cpwdtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.cPwdType
     *
     * @param cpwdtype the value for dbo.AOS_RMS_USER.cPwdType
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCpwdtype(String cpwdtype) {
        this.cpwdtype = cpwdtype == null ? null : cpwdtype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.dNameStartDate
     *
     * @return the value of dbo.AOS_RMS_USER.dNameStartDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getDnamestartdate() {
        return dnamestartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.dNameStartDate
     *
     * @param dnamestartdate the value for dbo.AOS_RMS_USER.dNameStartDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setDnamestartdate(String dnamestartdate) {
        this.dnamestartdate = dnamestartdate == null ? null : dnamestartdate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.dNameEndDate
     *
     * @return the value of dbo.AOS_RMS_USER.dNameEndDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getDnameenddate() {
        return dnameenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.dNameEndDate
     *
     * @param dnameenddate the value for dbo.AOS_RMS_USER.dNameEndDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setDnameenddate(String dnameenddate) {
        this.dnameenddate = dnameenddate == null ? null : dnameenddate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.dPwdStartDate
     *
     * @return the value of dbo.AOS_RMS_USER.dPwdStartDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getDpwdstartdate() {
        return dpwdstartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.dPwdStartDate
     *
     * @param dpwdstartdate the value for dbo.AOS_RMS_USER.dPwdStartDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setDpwdstartdate(String dpwdstartdate) {
        this.dpwdstartdate = dpwdstartdate == null ? null : dpwdstartdate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.dPwdEndDate
     *
     * @return the value of dbo.AOS_RMS_USER.dPwdEndDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getDpwdenddate() {
        return dpwdenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.dPwdEndDate
     *
     * @param dpwdenddate the value for dbo.AOS_RMS_USER.dPwdEndDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setDpwdenddate(String dpwdenddate) {
        this.dpwdenddate = dpwdenddate == null ? null : dpwdenddate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.iConcurrent
     *
     * @return the value of dbo.AOS_RMS_USER.iConcurrent
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Integer getIconcurrent() {
        return iconcurrent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.iConcurrent
     *
     * @param iconcurrent the value for dbo.AOS_RMS_USER.iConcurrent
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setIconcurrent(Integer iconcurrent) {
        this.iconcurrent = iconcurrent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.iFirstLogin
     *
     * @return the value of dbo.AOS_RMS_USER.iFirstLogin
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Integer getIfirstlogin() {
        return ifirstlogin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.iFirstLogin
     *
     * @param ifirstlogin the value for dbo.AOS_RMS_USER.iFirstLogin
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setIfirstlogin(Integer ifirstlogin) {
        this.ifirstlogin = ifirstlogin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.cAuthType
     *
     * @return the value of dbo.AOS_RMS_USER.cAuthType
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCauthtype() {
        return cauthtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.cAuthType
     *
     * @param cauthtype the value for dbo.AOS_RMS_USER.cAuthType
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCauthtype(String cauthtype) {
        this.cauthtype = cauthtype == null ? null : cauthtype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.cIDENTITY
     *
     * @return the value of dbo.AOS_RMS_USER.cIDENTITY
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCidentity() {
        return cidentity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.cIDENTITY
     *
     * @param cidentity the value for dbo.AOS_RMS_USER.cIDENTITY
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCidentity(String cidentity) {
        this.cidentity = cidentity == null ? null : cidentity.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.cDESCP
     *
     * @return the value of dbo.AOS_RMS_USER.cDESCP
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCdescp() {
        return cdescp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.cDESCP
     *
     * @param cdescp the value for dbo.AOS_RMS_USER.cDESCP
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCdescp(String cdescp) {
        this.cdescp = cdescp == null ? null : cdescp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.iIfLoginSys
     *
     * @return the value of dbo.AOS_RMS_USER.iIfLoginSys
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Integer getIifloginsys() {
        return iifloginsys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.iIfLoginSys
     *
     * @param iifloginsys the value for dbo.AOS_RMS_USER.iIfLoginSys
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setIifloginsys(Integer iifloginsys) {
        this.iifloginsys = iifloginsys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.AOS_RMS_USER.iSystemUser
     *
     * @return the value of dbo.AOS_RMS_USER.iSystemUser
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Integer getIsystemuser() {
        return isystemuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.AOS_RMS_USER.iSystemUser
     *
     * @param isystemuser the value for dbo.AOS_RMS_USER.iSystemUser
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setIsystemuser(Integer isystemuser) {
        this.isystemuser = isystemuser;
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }
    
    
}