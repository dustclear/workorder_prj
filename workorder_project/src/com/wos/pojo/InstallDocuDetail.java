package com.wos.pojo;

import java.util.Date;
import java.util.List;

public class InstallDocuDetail {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cGUID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cguid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cMainID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cmainid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cCode
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String ccode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cName
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cRelationMatID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String crelationmatid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cContactID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String ccontactid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cInstallDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Date cinstalldate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cInstallTypeID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cinstalltypeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cVersion
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cversion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cIsMain
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Integer cismain;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cIsStatus
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Integer cisstatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cChargeTypeID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cchargetypeid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cResponseID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cresponseid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cServiceData
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Date cservicedata;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cServiceStartDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Date cservicestartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cGuaranteeStartDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Date cguaranteestartdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cGuaranteeEndDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Date cguaranteeenddate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cBarCode
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private String cbarcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.itsm_service_InstallDocuDetail.cisinput
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    private Integer cisinput;
    
    //manual add items
    private Material relateMaterial;
    
    private EnterpriseContacts contactInfo;
    
    private ChargeType chargeType;
    
    private ServiceResponse serviceResponse;
    
    private ConfigInfo installType;
    
    //扩展属性
    private List<InstallDocuCofig> installDocuCofigs;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cGUID
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cGUID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCguid() {
        return cguid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cGUID
     *
     * @param cguid the value for dbo.itsm_service_InstallDocuDetail.cGUID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCguid(String cguid) {
        this.cguid = cguid == null ? null : cguid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cMainID
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cMainID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCmainid() {
        return cmainid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cMainID
     *
     * @param cmainid the value for dbo.itsm_service_InstallDocuDetail.cMainID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCmainid(String cmainid) {
        this.cmainid = cmainid == null ? null : cmainid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cCode
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cCode
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCcode() {
        return ccode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cCode
     *
     * @param ccode the value for dbo.itsm_service_InstallDocuDetail.cCode
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCcode(String ccode) {
        this.ccode = ccode == null ? null : ccode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cName
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cName
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCname() {
        return cname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cName
     *
     * @param cname the value for dbo.itsm_service_InstallDocuDetail.cName
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cRelationMatID
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cRelationMatID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCrelationmatid() {
        return crelationmatid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cRelationMatID
     *
     * @param crelationmatid the value for dbo.itsm_service_InstallDocuDetail.cRelationMatID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCrelationmatid(String crelationmatid) {
        this.crelationmatid = crelationmatid == null ? null : crelationmatid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cContactID
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cContactID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCcontactid() {
        return ccontactid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cContactID
     *
     * @param ccontactid the value for dbo.itsm_service_InstallDocuDetail.cContactID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCcontactid(String ccontactid) {
        this.ccontactid = ccontactid == null ? null : ccontactid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cInstallDate
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cInstallDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Date getCinstalldate() {
        return cinstalldate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cInstallDate
     *
     * @param cinstalldate the value for dbo.itsm_service_InstallDocuDetail.cInstallDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCinstalldate(Date cinstalldate) {
        this.cinstalldate = cinstalldate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cInstallTypeID
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cInstallTypeID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCinstalltypeid() {
        return cinstalltypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cInstallTypeID
     *
     * @param cinstalltypeid the value for dbo.itsm_service_InstallDocuDetail.cInstallTypeID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCinstalltypeid(String cinstalltypeid) {
        this.cinstalltypeid = cinstalltypeid == null ? null : cinstalltypeid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cVersion
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cVersion
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCversion() {
        return cversion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cVersion
     *
     * @param cversion the value for dbo.itsm_service_InstallDocuDetail.cVersion
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCversion(String cversion) {
        this.cversion = cversion == null ? null : cversion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cIsMain
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cIsMain
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Integer getCismain() {
        return cismain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cIsMain
     *
     * @param cismain the value for dbo.itsm_service_InstallDocuDetail.cIsMain
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCismain(Integer cismain) {
        this.cismain = cismain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cIsStatus
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cIsStatus
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Integer getCisstatus() {
        return cisstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cIsStatus
     *
     * @param cisstatus the value for dbo.itsm_service_InstallDocuDetail.cIsStatus
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCisstatus(Integer cisstatus) {
        this.cisstatus = cisstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cChargeTypeID
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cChargeTypeID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCchargetypeid() {
        return cchargetypeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cChargeTypeID
     *
     * @param cchargetypeid the value for dbo.itsm_service_InstallDocuDetail.cChargeTypeID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCchargetypeid(String cchargetypeid) {
        this.cchargetypeid = cchargetypeid == null ? null : cchargetypeid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cResponseID
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cResponseID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCresponseid() {
        return cresponseid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cResponseID
     *
     * @param cresponseid the value for dbo.itsm_service_InstallDocuDetail.cResponseID
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCresponseid(String cresponseid) {
        this.cresponseid = cresponseid == null ? null : cresponseid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cServiceData
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cServiceData
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Date getCservicedata() {
        return cservicedata;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cServiceData
     *
     * @param cservicedata the value for dbo.itsm_service_InstallDocuDetail.cServiceData
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCservicedata(Date cservicedata) {
        this.cservicedata = cservicedata;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cServiceStartDate
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cServiceStartDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Date getCservicestartdate() {
        return cservicestartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cServiceStartDate
     *
     * @param cservicestartdate the value for dbo.itsm_service_InstallDocuDetail.cServiceStartDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCservicestartdate(Date cservicestartdate) {
        this.cservicestartdate = cservicestartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cGuaranteeStartDate
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cGuaranteeStartDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Date getCguaranteestartdate() {
        return cguaranteestartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cGuaranteeStartDate
     *
     * @param cguaranteestartdate the value for dbo.itsm_service_InstallDocuDetail.cGuaranteeStartDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCguaranteestartdate(Date cguaranteestartdate) {
        this.cguaranteestartdate = cguaranteestartdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cGuaranteeEndDate
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cGuaranteeEndDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Date getCguaranteeenddate() {
        return cguaranteeenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cGuaranteeEndDate
     *
     * @param cguaranteeenddate the value for dbo.itsm_service_InstallDocuDetail.cGuaranteeEndDate
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCguaranteeenddate(Date cguaranteeenddate) {
        this.cguaranteeenddate = cguaranteeenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cBarCode
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cBarCode
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public String getCbarcode() {
        return cbarcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cBarCode
     *
     * @param cbarcode the value for dbo.itsm_service_InstallDocuDetail.cBarCode
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCbarcode(String cbarcode) {
        this.cbarcode = cbarcode == null ? null : cbarcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.itsm_service_InstallDocuDetail.cisinput
     *
     * @return the value of dbo.itsm_service_InstallDocuDetail.cisinput
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public Integer getCisinput() {
        return cisinput;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.itsm_service_InstallDocuDetail.cisinput
     *
     * @param cisinput the value for dbo.itsm_service_InstallDocuDetail.cisinput
     *
     * @mbggenerated Mon Dec 01 16:05:56 CST 2014
     */
    public void setCisinput(Integer cisinput) {
        this.cisinput = cisinput;
    }

	public Material getRelateMaterial() {
		return relateMaterial;
	}

	public void setRelateMaterial(Material relateMaterial) {
		this.relateMaterial = relateMaterial;
	}

	public EnterpriseContacts getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(EnterpriseContacts contactInfo) {
		this.contactInfo = contactInfo;
	}

	public ChargeType getChargeType() {
		return chargeType;
	}

	public void setChargeType(ChargeType chargeType) {
		this.chargeType = chargeType;
	}

	public ServiceResponse getServiceResponse() {
		return serviceResponse;
	}

	public void setServiceResponse(ServiceResponse serviceResponse) {
		this.serviceResponse = serviceResponse;
	}

    public List<InstallDocuCofig> getInstallDocuCofigs()
    {
        return installDocuCofigs;
    }

    public void setInstallDocuCofigs(List<InstallDocuCofig> installDocuCofigs)
    {
        this.installDocuCofigs = installDocuCofigs;
    }

	public ConfigInfo getInstallType() {
		return installType;
	}

	public void setInstallType(ConfigInfo installType) {
		this.installType = installType;
	}
    
    
}