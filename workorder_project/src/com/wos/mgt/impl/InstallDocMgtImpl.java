package com.wos.mgt.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wos.common.WosConstant;
import com.wos.common.WosHelper;
import com.wos.dao.mapper.AddressTypeMapper;
import com.wos.dao.mapper.ChargeTypeMapper;
import com.wos.dao.mapper.ConfigInfoMapper;
import com.wos.dao.mapper.ContactInfoMapper;
import com.wos.dao.mapper.EnterpriseAddressMapper;
import com.wos.dao.mapper.EnterpriseContactsMapper;
import com.wos.dao.mapper.EventInfoMapper;
import com.wos.dao.mapper.ExtendedAttributeMapper;
import com.wos.dao.mapper.InstallDetailMapper;
import com.wos.dao.mapper.InstallDocuDetailMapper;
import com.wos.dao.mapper.InstallDocumentMapper;
import com.wos.dao.mapper.InstallTemplateMapper;
import com.wos.dao.mapper.RmsUserMapper;
import com.wos.dao.mapper.ServiceResponseMapper;
import com.wos.dao.mapper.TaxOrganizationMapper;
import com.wos.mgt.InstallDocMgt;
import com.wos.pojo.ChargeType;
import com.wos.pojo.ConfigInfo;
import com.wos.pojo.ContactInfo;
import com.wos.pojo.EnterpriseAddress;
import com.wos.pojo.EnterpriseContacts;
import com.wos.pojo.EventInfo;
import com.wos.pojo.ExtendedAttribute;
import com.wos.pojo.InstallDetail;
import com.wos.pojo.InstallDocuCofig;
import com.wos.pojo.InstallDocuDetail;
import com.wos.pojo.InstallDocument;
import com.wos.pojo.InstallTemplate;
import com.wos.pojo.RmsUser;
import com.wos.pojo.ServiceResponse;
import com.wos.pojo.TaxOrganization;

@WebService(endpointInterface = "com.wos.mgt.InstallDocMgt")
public class InstallDocMgtImpl implements InstallDocMgt
{
    @Resource
    WebServiceContext wsContext;
    
    private RmsUserMapper rmsUserMapper;
    
    private EventInfoMapper eventInfoMapper;
    
    private AddressTypeMapper addressTypeMapper;
    
    private InstallDocumentMapper installDocumentMapper;
    
    private InstallTemplateMapper installTemplateMapper;
    
    private InstallDetailMapper installDetailMapper;
    
    private InstallDocuDetailMapper installDocuDetailMapper;
    
    private TaxOrganizationMapper taxOrganizationMapper;
    
    private ConfigInfoMapper configInfoMapper;
    
    private ChargeTypeMapper chargeTypeMapper;
    
    private ServiceResponseMapper serviceResponseMapper;
    
    private ContactInfoMapper contactInfoMapper;
    
    private ExtendedAttributeMapper extendedAttributeMapper;
    
    private EnterpriseAddressMapper enterpriseAddressMapper;
    
    private EnterpriseContactsMapper enterpriseContactMapper;
    
    private static final Gson _gson = new GsonBuilder().setDateFormat(WosConstant.DATE_TIME_FORMAT)
            .create();
    
    private WosHelper _helper = WosHelper.getInstance();
    
    private RmsUser currentUser;
    
    private InstallDocument currentInstallDocument;
    
    @Override
    public String loadInstallDocumentByEventCode(String argEventCodeText)
    {
        
        String eventCode = _helper.getValueFromJsonText(argEventCodeText,
                "ccode");
        String userId = _helper.getValueFromJsonText(argEventCodeText, "cguid"); // 此处的cguid为userid，对应表AOS_RMS_USER的cguid
        HttpSession session = _helper.getSession(wsContext);
        
        if (StringUtils.isNotEmpty(userId))
        {
            currentUser = rmsUserMapper.findUserById(userId);
        }
        else if (session != null)
        {
            currentUser = (RmsUser)session.getAttribute(WosConstant.CURRENT_USER);
        }
        
        EventInfo eventInfo = eventInfoMapper.loadEventInfoByEventCode(eventCode);
        InstallDocument doc = createInstallDocumentFromEventInfo(eventInfo);
        
        if (session != null)
        {
            session.setAttribute(WosConstant.CURRENT_INSTALL_DOCUMENT, doc);
        }
        currentInstallDocument = doc;
        _helper.toJsonText(doc, InstallDocument.class);
        return _helper.toJsonText(doc, InstallDocument.class);
    }
    
    @Override
    public String getAllInstallTemplates()
    {
        List<InstallTemplate> templates = installTemplateMapper.loadAllInstallTemplates();
        
        return _helper.toJsonText(templates, null);
    }
    
    @Override
    public String getInstallDetailByTemplate(String argInstallTemplateText)
    {
        String installTemplateId = _helper.getValueFromJsonText(argInstallTemplateText,
                "installTemplateId");
        
        List<InstallDetail> installDetails = installDetailMapper.findInstallDetailByTemplateId(installTemplateId);
        
        List<InstallDocuDetail> installDocuDetails = createInstallDocDetailsFromInstallDetail(installDetails);
        
        return _helper.toJsonText(installDocuDetails, null);
    }
    
    @Override
    public String getAllTaxOrganizations()
    {
        List<TaxOrganization> taxOrganizations = taxOrganizationMapper.loadAllTaxOrganizations();
        return _helper.toJsonText(taxOrganizations, null);
    }
    
    @Override
    public String getTaxOrganizationsByParentId(String argParentIdText)
    {
        String parentId = _helper.getValueFromJsonText(argParentIdText,
                "cparentid");
        List<TaxOrganization> taxOrganizations = taxOrganizationMapper.findTaxOrganizationsByParentCode(parentId);
        return _helper.toJsonText(taxOrganizations, null);
    }
    
    @Override
    public String getTaxOrganizationsByName(String argOrgNameText)
    {
        String orgName = _helper.getValueFromJsonText(argOrgNameText, "cName");
        List<TaxOrganization> taxOrganizations = taxOrganizationMapper.findTaxOrganizationByName(orgName);
        
        return _helper.toJsonText(taxOrganizations, null);
    }
    
    @Override
    public String getEnterpriseContactInfo(String argEnterpriseIdText)
    {
        String enterpriseId = _helper.getValueFromJsonText(argEnterpriseIdText,
                "cEnterpriseID");
        // List<ContactInfo> contactInfos =
        // contactInfo.findContactInfoByEnterpriseId(enterpriseId);
        List<EnterpriseContacts> enterpriseContactRes = enterpriseContactMapper.findContactInfoByEnterpriseId(enterpriseId);
        
        return _helper.toJsonText(enterpriseContactRes, null);
    }
    
    @Override
    public String getContactInfoById(String argContactIdText)
    {
        String contactInfoId = _helper.getValueFromJsonText(argContactIdText,
                "cGUID");
        List<ContactInfo> contactInfos = contactInfoMapper.findContactInfoById(contactInfoId);
        
        return _helper.toJsonText(contactInfos.get(0), null);
    }
    
    @Override
    public String saveTelephone(String argTtelephonesText)
    {
        ContactInfo contactInfoUpdate = _gson.fromJson(argTtelephonesText,
                ContactInfo.class);
        
        int result = contactInfoMapper.updateContactPhone(contactInfoUpdate);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String saveCellphone(String argCellPhonesText)
    {
        return saveTelephone(argCellPhonesText);
    }
    
    @Override
    public String addContactInfo(String argContactInfoText)
    {
        ContactInfo contactInfoRes = _gson.fromJson(argContactInfoText,
                ContactInfo.class);
        contactInfoRes.setCguid(_helper.generatePrimaryKey());
        
        int result = contactInfoMapper.insertSelective(contactInfoRes);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String deleteContactInfo(String argContactIdText)
    {
        String cguid = _helper.getValueFromJsonText(argContactIdText, "cguid");
        int result = contactInfoMapper.deleteByPrimaryKey(cguid);
        
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String updateContactInfo(String argContactInfoText)
    {
        ContactInfo contactInfoUpdate = _gson.fromJson(argContactInfoText,
                ContactInfo.class);
        
        int result = contactInfoMapper.updateContactInfo(contactInfoUpdate);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String selectAsCurrentContact(String argCurrentContactText)
    {
        String cguid = _helper.getValueFromJsonText(argCurrentContactText,
                "cguid");
        
        enterpriseContactMapper.removeCurrentContact(cguid);
        int result = enterpriseContactMapper.updateAsCurrentContact(cguid);
        
        EnterpriseContacts newEnterpriseContact = enterpriseContactMapper.findContactInfoByPrimaryId(cguid);
        
        updateContactToInstallDoc(newEnterpriseContact.getCcontactid(),
                newEnterpriseContact.getCenterpriseid());
        
        return _helper.toJsonText(result, null);
    }
    
    private void updateContactToInstallDoc(String newContactId,
            String enterpriseId)
    {
        List<ContactInfo> newContactInfos = contactInfoMapper.findContactInfoById(newContactId);
        
        // update contact info in install document
        if (newContactInfos != null && newContactInfos.size() > 0)
        {
            ContactInfo newContactInfo = newContactInfos.get(0);
            List<InstallDocument> documents = installDocumentMapper.findInstallDocumentByEnterpriseId(enterpriseId);
            for (InstallDocument document : documents)
            {
                if (document != null)
                {
                    document.setCcontactid(newContactId);
                    document.setCcontactname(newContactInfo.getCname());
                    document.setCcontactphone(newContactInfo.getCphone1());
                    document.setCcontacttel(newContactInfo.getCtel1());
                    
                    installDocumentMapper.updateContactInfo(document);
                }
            }
            
        }
    }
    
    @Override
    public String getEnterpriseAddresses(String argEnterpriseIdText)
    {
        String enterpriseId = _helper.getValueFromJsonText(argEnterpriseIdText,
                "centerpriseid");
        
        List<EnterpriseAddress> enterpriseAddresses = enterpriseAddressMapper.getEnterpriseAddresses(enterpriseId);
        
        return _helper.toJsonText(enterpriseAddresses, null);
    }
    
    @Override
    public String addEnterpriseAddress(String argAddressText)
    {
        EnterpriseAddress enterpriseAddressNew = _gson.fromJson(argAddressText,
                EnterpriseAddress.class);
        enterpriseAddressNew.setCguid(_helper.generatePrimaryKey());
        
        int result = enterpriseAddressMapper.insertSelective(enterpriseAddressNew);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String updateEnterpriseAddress(String argAddressText)
    {
        EnterpriseAddress enterpriseAddressUpdate = _gson.fromJson(argAddressText,
                EnterpriseAddress.class);
        
        int result = enterpriseAddressMapper.updateEnterpriseAddress(enterpriseAddressUpdate);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String deleteEnterpriseAddress(String argAddressIdText)
    {
        String cguid = _helper.getValueFromJsonText(argAddressIdText, "cguid");
        int result = enterpriseAddressMapper.deleteByPrimaryKey(cguid);
        
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String selectAsCurrentAddress(String argCurrentAddressText)
    {
        String cguid = _helper.getValueFromJsonText(argCurrentAddressText,
                "cguid");
        int result = enterpriseAddressMapper.updateAsCurrentAddress(cguid);
        
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String loadAllInstallTypes()
    {
        List<ConfigInfo> configInfos = configInfoMapper.loadAllConfigInfos();
        
        return _helper.toJsonText(configInfos, null);
    }
    
    @Override
    public String loadAllChargeTypes()
    {
        List<ChargeType> chargeTypes = chargeTypeMapper.loadAllChargeTypes();
        
        return _helper.toJsonText(chargeTypes, null);
    }
    
    @Override
    public String loadAllServiceResponses()
    {
        List<ServiceResponse> serviceResponses = serviceResponseMapper.loadAllServiceResponses();
        return _helper.toJsonText(serviceResponses, null);
    }
    
    @Override
    public String loadExtendedAttributesByMatId(String argMatIdText)
    {
        String matId = _helper.getValueFromJsonText(argMatIdText, "cmatid");
        
        List<ExtendedAttribute> extendedAttributes = extendedAttributeMapper.findExtendedAttributesByMatId(matId);
        
        return _helper.toJsonText(extendedAttributes, null);
    }
    
    @Override
    public String saveInstallDocDetail(String installDetailText)
    {
        InstallDocuDetail installDocuDetailUpdate = _gson.fromJson(installDetailText,
                InstallDocuDetail.class);
        
        int result = installDocuDetailMapper.updateByPrimaryKeySelective(installDocuDetailUpdate);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String addInstallDocDetail(String installDetailText)
    {
        InstallDocuDetail installDocuDetailNew = _gson.fromJson(installDetailText,
                InstallDocuDetail.class);
        installDocuDetailNew.setCguid(_helper.generatePrimaryKey());
        
        int result = installDocuDetailMapper.insertSelective(installDocuDetailNew);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String saveInstallDocument(String installDocumentText)
    {
        InstallDocument installDocumentUpdate = _gson.fromJson(installDocumentText,
                InstallDocument.class);
        
        int result = installDocumentMapper.updateByPrimaryKeySelective(installDocumentUpdate);
        return _helper.toJsonText(result, null);
    }
    
    /**
     * get set methods
     */
    
    public EventInfoMapper getEventInfoMapper()
    {
        return eventInfoMapper;
    }
    
    public AddressTypeMapper getAddressTypeMapper()
    {
        return addressTypeMapper;
    }
    
    public void setAddressTypeMapper(AddressTypeMapper addressTypeMapper)
    {
        this.addressTypeMapper = addressTypeMapper;
    }
    
    public InstallDocumentMapper getInstallDocumentMapper()
    {
        return installDocumentMapper;
    }
    
    public void setInstallDocumentMapper(
            InstallDocumentMapper installDocumentMapper)
    {
        this.installDocumentMapper = installDocumentMapper;
    }
    
    public InstallTemplateMapper getInstallTemplateMapper()
    {
        return installTemplateMapper;
    }
    
    public void setInstallTemplateMapper(
            InstallTemplateMapper installTemplateMapper)
    {
        this.installTemplateMapper = installTemplateMapper;
    }
    
    public InstallDocuDetailMapper getInstallDocuDetailMapper()
    {
        return installDocuDetailMapper;
    }
    
    public void setInstallDocuDetailMapper(
            InstallDocuDetailMapper installDocuDetailMapper)
    {
        this.installDocuDetailMapper = installDocuDetailMapper;
    }
    
    public TaxOrganizationMapper getTaxOrganizationMapper()
    {
        return taxOrganizationMapper;
    }
    
    public void setTaxOrganizationMapper(
            TaxOrganizationMapper taxOrganizationMapper)
    {
        this.taxOrganizationMapper = taxOrganizationMapper;
    }
    
    public ConfigInfoMapper getConfigInfoMapper()
    {
        return configInfoMapper;
    }
    
    public void setConfigInfoMapper(ConfigInfoMapper configInfoMapper)
    {
        this.configInfoMapper = configInfoMapper;
    }
    
    public ChargeTypeMapper getChargeTypeMapper()
    {
        return chargeTypeMapper;
    }
    
    public void setChargeTypeMapper(ChargeTypeMapper chargeTypeMapper)
    {
        this.chargeTypeMapper = chargeTypeMapper;
    }
    
    public ServiceResponseMapper getServiceResponseMapper()
    {
        return serviceResponseMapper;
    }
    
    public void setServiceResponseMapper(
            ServiceResponseMapper serviceResponseMapper)
    {
        this.serviceResponseMapper = serviceResponseMapper;
    }
    
    public ContactInfoMapper getContactInfoMapper()
    {
        return contactInfoMapper;
    }
    
    public void setContactInfoMapper(ContactInfoMapper contactInfoMapper)
    {
        this.contactInfoMapper = contactInfoMapper;
    }
    
    public ExtendedAttributeMapper getExtendedAttributeMapper()
    {
        return extendedAttributeMapper;
    }
    
    public void setExtendedAttributeMapper(
            ExtendedAttributeMapper extendedAttributeMapper)
    {
        this.extendedAttributeMapper = extendedAttributeMapper;
    }
    
    public EnterpriseAddressMapper getEnterpriseAddressMapper()
    {
        return enterpriseAddressMapper;
    }
    
    public void setEnterpriseAddressMapper(
            EnterpriseAddressMapper enterpriseAddressMapper)
    {
        this.enterpriseAddressMapper = enterpriseAddressMapper;
    }
    
    public EnterpriseContactsMapper getEnterpriseContactMapper()
    {
        return enterpriseContactMapper;
    }
    
    public void setEnterpriseContactMapper(
            EnterpriseContactsMapper enterpriseContactMapper)
    {
        this.enterpriseContactMapper = enterpriseContactMapper;
    }
    
    public void setEventInfoMapper(EventInfoMapper eventInfoMapper)
    {
        this.eventInfoMapper = eventInfoMapper;
    }
    
    public InstallDetailMapper getInstallDetailMapper()
    {
        return installDetailMapper;
    }
    
    public void setInstallDetailMapper(InstallDetailMapper installDetailMapper)
    {
        this.installDetailMapper = installDetailMapper;
    }
    
    public RmsUserMapper getRmsUserMapper()
    {
        return rmsUserMapper;
    }
    
    public void setRmsUserMapper(RmsUserMapper rmsUserMapper)
    {
        this.rmsUserMapper = rmsUserMapper;
    }
    
    private InstallDocument createInstallDocumentFromEventInfo(
            EventInfo eventInfo)
    {
        InstallDocument newInstallDocument = null;
        if (eventInfo != null)
        {
            try
            {
                newInstallDocument = new InstallDocument();
                newInstallDocument.setCguid(_helper.generatePrimaryKey());
                
                newInstallDocument.setCeventid(eventInfo.getCguid());
                // 纸质单号
                newInstallDocument.setCcode(_helper.gernerateInstallCode());
                // 企业信息
                newInstallDocument.setEnterpriseBaseInfo(eventInfo.getEnterpriseBaseInfo());
                // 企业信息快照
                newInstallDocument.setCenterpriseid(eventInfo.getCenterpriseid());
                newInstallDocument.setCenterprisename(eventInfo.getCenterprisename());
                newInstallDocument.setCtaxcode(eventInfo.getCtaxcode());
                
                newInstallDocument.setCenterpriseadress(eventInfo.getCenterpriseadress());
                newInstallDocument.setCenterprisedepartment(null);
                newInstallDocument.setCcontactid(eventInfo.getCcontactid());
                newInstallDocument.setCcontactname(eventInfo.getCcontactname());
                newInstallDocument.setCcontacttel(eventInfo.getCcontacttel());
                newInstallDocument.setCcontactphone(eventInfo.getCcontactphone());
                newInstallDocument.setCarea(eventInfo.getAreaClass().getCname());
                
                if (currentUser != null && currentUser.getEmployee() != null)
                {
                    newInstallDocument.setEmployee(currentUser.getEmployee());
                    newInstallDocument.setCemployeeid(currentUser.getEmployee()
                            .getCguid());
                    if (currentUser.getEmployee().getDepartment() != null)
                    {
                        newInstallDocument.setCdepartment(currentUser.getEmployee()
                                .getDepartment()
                                .getCname());
                    }
                    
                }
                
                if (eventInfo.getEnterpriseBaseInfo() != null
                        && eventInfo.getEnterpriseBaseInfo()
                                .getCurrentTaxOrganization() != null)
                {
                    newInstallDocument.setCbelongtax(eventInfo.getEnterpriseBaseInfo()
                            .getCurrentTaxOrganization()
                            .getCguid());
                }
                
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
        }
        
        return newInstallDocument;
    }
    
    private List<InstallDocuDetail> createInstallDocDetailsFromInstallDetail(
            List<InstallDetail> argInstallDetails)
    {
        List<InstallDocuDetail> installDocuDetails = new ArrayList<InstallDocuDetail>();
        
        for (InstallDetail installDetail : argInstallDetails)
        {
            try
            {
                InstallDocuDetail newInstallDocuDetail = new InstallDocuDetail();
                newInstallDocuDetail.setCguid(_helper.generatePrimaryKey());
                
                newInstallDocuDetail.setCcode(null);
                newInstallDocuDetail.setCname(installDetail.getMaterial()
                        .getCname());
                newInstallDocuDetail.setCrelationmatid(installDetail.getCmatid());
                newInstallDocuDetail.setRelateMaterial(installDetail.getMaterial());
                
                newInstallDocuDetail.setCinstalltypeid(installDetail.getCinstalltypeid());
                newInstallDocuDetail.setCversion(installDetail.getMaterial()
                        .getCspec());
                newInstallDocuDetail.setCismain(installDetail.getCismain());
                newInstallDocuDetail.setCisstatus(null);
                newInstallDocuDetail.setCchargetypeid(installDetail.getCchargetype());
                newInstallDocuDetail.setChargeType(installDetail.getChargeType());
                newInstallDocuDetail.setCresponseid(installDetail.getCresponseid());
                newInstallDocuDetail.setServiceResponse(installDetail.getServiceResponse());
                newInstallDocuDetail.setCservicedata(new Date());
                newInstallDocuDetail.setCservicestartdate(new Date());
                newInstallDocuDetail.setCguaranteestartdate(new Date());
                newInstallDocuDetail.setCguaranteeenddate(new Date());
                
                if (currentInstallDocument != null)
                {
                    newInstallDocuDetail.setCmainid(currentInstallDocument.getCguid());
                    newInstallDocuDetail.setCcontactid(currentInstallDocument.getCcontactid());
                    if (currentInstallDocument.getEnterpriseBaseInfo()!=null)
                    {
                        newInstallDocuDetail.setContactInfo(currentInstallDocument.getEnterpriseBaseInfo()
                                .getCurrentContact());
                    }
                }
                // 创建扩展属性
                createExtendedAttrsForInstallDocDetail(newInstallDocuDetail);
                
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
        }
        
        return installDocuDetails;
    }
    
    private void createExtendedAttrsForInstallDocDetail(
            InstallDocuDetail installDocuDetail)
    {
        if (installDocuDetail.getRelateMaterial() != null)
        {
            List<ExtendedAttribute> extendedAttrs = installDocuDetail.getRelateMaterial()
                    .getExtendedAttributes();
            if (extendedAttrs != null && extendedAttrs.size() > 0)
            {
                List<InstallDocuCofig> installDocuCofigs = new ArrayList<InstallDocuCofig>();
                for (ExtendedAttribute extendedAttr : extendedAttrs)
                {
                    InstallDocuCofig installDocuCofig = new InstallDocuCofig();
                    installDocuCofig.setCguid(_helper.generatePrimaryKey());
                    
                    installDocuCofig.setCname(extendedAttr.getCname());
                    installDocuCofig.setCvalue(null);
                    installDocuCofig.setCid(null);
                    installDocuCofig.setCmainid(installDocuDetail.getCguid());
                    installDocuCofig.setCextid(null);
                    // 扩展属性可选值
                    installDocuCofig.setEnumerateInfos(extendedAttr.getEnumerateInfos());
                    
                    installDocuCofigs.add(installDocuCofig);
                }
                installDocuDetail.setInstallDocuCofigs(installDocuCofigs);
                
            }
        }
    }
    
}