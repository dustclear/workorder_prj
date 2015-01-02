package com.wos.mgt.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.transport.http.AbstractHTTPDestination;

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
import com.wos.dao.mapper.ExtendedAttributeMapper;
import com.wos.dao.mapper.InstallDocuDetailMapper;
import com.wos.dao.mapper.InstallDocumentMapper;
import com.wos.dao.mapper.InstallTemplateMapper;
import com.wos.dao.mapper.ServiceResponseMapper;
import com.wos.dao.mapper.TaxOrganizationMapper;
import com.wos.mgt.InstallDocMgt;
import com.wos.pojo.ChargeType;
import com.wos.pojo.ConfigInfo;
import com.wos.pojo.ContactInfo;
import com.wos.pojo.EnterpriseAddress;
import com.wos.pojo.EnterpriseContacts;
import com.wos.pojo.ExtendedAttribute;
import com.wos.pojo.InstallDocuDetail;
import com.wos.pojo.InstallDocument;
import com.wos.pojo.InstallTemplate;
import com.wos.pojo.ServiceResponse;
import com.wos.pojo.TaxOrganization;

@WebService(endpointInterface = "com.wos.mgt.InstallDocMgt")
public class InstallDocMgtImpl implements InstallDocMgt
{
    @Resource
    WebServiceContext wsContext;
    
    private AddressTypeMapper addressType;
    
    private InstallDocumentMapper installDocument;
    
    private InstallTemplateMapper installTemplate;
    
    private InstallDocuDetailMapper installDocuDetail;
    
    private TaxOrganizationMapper taxOrganization;
    
    private ConfigInfoMapper configInfo;
    
    private ChargeTypeMapper chargeType;
    
    private ServiceResponseMapper serviceResponse;
    
    private ContactInfoMapper contactInfo;
    
    private ExtendedAttributeMapper extendedAttribute;
    
    private EnterpriseAddressMapper enterpriseAddress;
    
    private EnterpriseContactsMapper enterpriseContact;
    
    private static final Gson _gson = new GsonBuilder().setDateFormat(WosConstant.DATE_TIME_FORMAT).create();
    
    private WosHelper _helper = WosHelper.getInstance();
    
    @Override
    public String loadInstallDocumentByEventCode(String argEventCodeText)
    {
        String eventCode = _helper.getValueFromJsonText(argEventCodeText,
                "ceventid");
        
        InstallDocument doc = installDocument.findInstallDocumentByEventCode(eventCode);
        /*   
           if (getSession() != null)
           {
               getSession().setAttribute(WosConstant.INSTALL_DOCUMENT, doc);
           }*/
        
        _helper.toJsonText(doc, InstallDocument.class);
        
        return _helper.toJsonText(doc, InstallDocument.class);
    }
    
    @Override
    public String getAllInstallTemplates()
    {
        List<InstallTemplate> templates = installTemplate.loadAllInstallTemplates();
        
        return _helper.toJsonText(templates, null);
    }
    
    @Override
    public String getInstallDetailByTemplate(String argInstallTemplateText)
    {
//        String installTemplate = _helper.getValueFromJsonText(argInstallTemplateText,
//                "installTemplate");
//        String cMainId = _helper.getValueFromJsonText(argInstallTemplateText,
//                "cMainId");
        
        Map<String, String> param = _gson.fromJson(argInstallTemplateText,
                Map.class);
        
        List<InstallDocuDetail> installDocuDetails = installDocuDetail.findInstallDetailByTemplate(param);
        
        return _helper.toJsonText(installDocuDetails, null);
    }
    
    @Override
    public String getAllTaxOrganizations()
    {
        List<TaxOrganization> taxOrganizations = taxOrganization.loadAllTaxOrganizations();
        return _helper.toJsonText(taxOrganizations, null);
    }
    
    @Override
    public String getTaxOrganizationsByParentId(String argParentIdText)
    {
        String parentId = _helper.getValueFromJsonText(argParentIdText,
                "cparentid");
        List<TaxOrganization> taxOrganizations = taxOrganization.findTaxOrganizationsByParentCode(parentId);
        return _helper.toJsonText(taxOrganizations, null);
    }
    
    @Override
    public String getTaxOrganizationsByName(String argOrgNameText)
    {
        String orgName = _helper.getValueFromJsonText(argOrgNameText, "cName");
        List<TaxOrganization> taxOrganizations = taxOrganization.findTaxOrganizationByName(orgName);
        
        return _helper.toJsonText(taxOrganizations, null);
    }
    
    @Override
    public String getEnterpriseContactInfo(String argEnterpriseIdText)
    {
        String enterpriseId = _helper.getValueFromJsonText(argEnterpriseIdText,
                "cEnterpriseID");
        //        List<ContactInfo> contactInfos = contactInfo.findContactInfoByEnterpriseId(enterpriseId);
        List<EnterpriseContacts> enterpriseContactRes = enterpriseContact.findContactInfoByEnterpriseId(enterpriseId);
        
        return _helper.toJsonText(enterpriseContactRes, null);
    }
    
    @Override
    public String getContactInfoById(String argContactIdText)
    {
        String contactInfoId = _helper.getValueFromJsonText(argContactIdText,
                "cGUID");
        List<ContactInfo> contactInfos = contactInfo.findContactInfoById(contactInfoId);
        
        return _helper.toJsonText(contactInfos.get(0), null);
    }
    
    @Override
    public String saveTelephone(String argTtelephonesText)
    {
        ContactInfo contactInfoUpdate = _gson.fromJson(argTtelephonesText,
                ContactInfo.class);
        
        int result = contactInfo.updateContactPhone(contactInfoUpdate);
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
        
        int result = contactInfo.insertSelective(contactInfoRes);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String deleteContactInfo(String argContactIdText)
    {
        String cguid = _helper.getValueFromJsonText(argContactIdText, "cguid");
        int result = contactInfo.deleteByPrimaryKey(cguid);
        
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String updateContactInfo(String argContactInfoText)
    {
        ContactInfo contactInfoUpdate = _gson.fromJson(argContactInfoText,
                ContactInfo.class);
        
        int result = contactInfo.updateContactInfo(contactInfoUpdate);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String selectAsCurrentContact(String argCurrentContactText)
    {
        String cguid = _helper.getValueFromJsonText(argCurrentContactText,
                "cguid");
        
        enterpriseContact.removeCurrentContact(cguid);
        int result = enterpriseContact.updateAsCurrentContact(cguid);
        
        EnterpriseContacts newEnterpriseContact = enterpriseContact.findContactInfoByPrimaryId(cguid);
        
        updateContactToInstallDoc(newEnterpriseContact.getCcontactid(),
                newEnterpriseContact.getCenterpriseid());
        
        return _helper.toJsonText(result, null);
    }
    
    private void updateContactToInstallDoc(String newContactId,
            String enterpriseId)
    {
        List<ContactInfo> newContactInfos = contactInfo.findContactInfoById(newContactId);
        
        //update contact info in install document
        if (newContactInfos != null && newContactInfos.size() > 0)
        {
            ContactInfo newContactInfo = newContactInfos.get(0);
            List<InstallDocument> documents = installDocument.findInstallDocumentByEnterpriseId(enterpriseId);
            for (InstallDocument document : documents)
            {
                if (document != null)
                {
                    document.setCcontactid(newContactId);
                    document.setCcontactname(newContactInfo.getCname());
                    document.setCcontactphone(newContactInfo.getCphone1());
                    document.setCcontacttel(newContactInfo.getCtel1());
                    
                    installDocument.updateContactInfo(document);
                }
            }
            
        }
    }
    
    @Override
    public String getEnterpriseAddresses(String argEnterpriseIdText)
    {
        String enterpriseId = _helper.getValueFromJsonText(argEnterpriseIdText,
                "centerpriseid");
        
        List<EnterpriseAddress> enterpriseAddresses = enterpriseAddress.getEnterpriseAddresses(enterpriseId);
        
        return _helper.toJsonText(enterpriseAddresses, null);
    }
    
    @Override
    public String addEnterpriseAddress(String argAddressText)
    {
        EnterpriseAddress enterpriseAddressNew = _gson.fromJson(argAddressText,
                EnterpriseAddress.class);
        enterpriseAddressNew.setCguid(_helper.generatePrimaryKey());
        
        int result = enterpriseAddress.insertSelective(enterpriseAddressNew);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String updateEnterpriseAddress(String argAddressText)
    {
        EnterpriseAddress enterpriseAddressUpdate = _gson.fromJson(argAddressText,
                EnterpriseAddress.class);
        
        int result = enterpriseAddress.updateEnterpriseAddress(enterpriseAddressUpdate);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String deleteEnterpriseAddress(String argAddressIdText)
    {
        String cguid = _helper.getValueFromJsonText(argAddressIdText, "cguid");
        int result = enterpriseAddress.deleteByPrimaryKey(cguid);
        
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String selectAsCurrentAddress(String argCurrentAddressText)
    {
        String cguid = _helper.getValueFromJsonText(argCurrentAddressText,
                "cguid");
        int result = enterpriseAddress.updateAsCurrentAddress(cguid);
        
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String loadAllInstallTypes()
    {
        List<ConfigInfo> configInfos = configInfo.loadAllConfigInfos();
        
        return _helper.toJsonText(configInfos, null);
    }
    
    @Override
    public String loadAllChargeTypes()
    {
        List<ChargeType> chargeTypes = chargeType.loadAllChargeTypes();
        
        return _helper.toJsonText(chargeTypes, null);
    }
    
    @Override
    public String loadAllServiceResponses()
    {
        List<ServiceResponse> serviceResponses = serviceResponse.loadAllServiceResponses();
        return _helper.toJsonText(serviceResponses, null);
    }
    
    @Override
    public String loadExtendedAttributesByMatId(String argMatIdText)
    {
        String matId = _helper.getValueFromJsonText(argMatIdText, "cmatid");
        
        List<ExtendedAttribute> extendedAttributes = extendedAttribute.findExtendedAttributesByMatId(matId);
        
        return _helper.toJsonText(extendedAttributes, null);
    }
    
    
    
    /* private HttpSession getSession()
     {
         HttpSession session = null;
         if (getRequest() != null)
         {
             session = getRequest().getSession(true);
         }
         return session;
     }
     
     private HttpServletRequest getRequest()
     {
         MessageContext mc;
         mc = wsContext.getMessageContext();
         
         HttpServletRequest request = (HttpServletRequest)mc.get(AbstractHTTPDestination.HTTP_REQUEST);
         
         return request;
     }*/
    
    @Override
    public String saveInstallDocDetail(String installDetailText)
    {
        InstallDocuDetail installDocuDetailUpdate = _gson.fromJson(installDetailText,
                InstallDocuDetail.class);
        
        int result = installDocuDetail.updateByPrimaryKeySelective(installDocuDetailUpdate);
        return _helper.toJsonText(result, null);
    }

    @Override
    public String addInstallDocDetail(String installDetailText)
    {
        InstallDocuDetail installDocuDetailNew = _gson.fromJson(installDetailText,
                InstallDocuDetail.class);
        installDocuDetailNew.setCguid(_helper.generatePrimaryKey());
        
        int result = installDocuDetail.insertSelective(installDocuDetailNew);
        return _helper.toJsonText(result, null);
    }

    @Override
    public String saveInstallDocument(String installDocumentText)
    {
        InstallDocument installDocumentUpdate = _gson.fromJson(installDocumentText,
                InstallDocument.class);
        
        int result = installDocument.updateByPrimaryKeySelective(installDocumentUpdate);
        return _helper.toJsonText(result, null);
    }
    
    

    /**
     * get set methods
     */
    
    public AddressTypeMapper getAddressType()
    {
        return addressType;
    }
    
    public void setAddressType(AddressTypeMapper argAddressType)
    {
        this.addressType = argAddressType;
    }
    
    public InstallDocumentMapper getInstallDocument()
    {
        return installDocument;
    }
    
    public void setInstallDocument(InstallDocumentMapper installDocument)
    {
        this.installDocument = installDocument;
    }
    
    public InstallTemplateMapper getInstallTemplate()
    {
        return installTemplate;
    }
    
    public void setInstallTemplate(InstallTemplateMapper installTemplate)
    {
        this.installTemplate = installTemplate;
    }
    
    public InstallDocuDetailMapper getInstallDocuDetail()
    {
        return installDocuDetail;
    }
    
    public void setInstallDocuDetail(InstallDocuDetailMapper installDocuDetail)
    {
        this.installDocuDetail = installDocuDetail;
    }
    
    public TaxOrganizationMapper getTaxOrganization()
    {
        return taxOrganization;
    }
    
    public void setTaxOrganization(TaxOrganizationMapper taxOrganization)
    {
        this.taxOrganization = taxOrganization;
    }
    
    public ConfigInfoMapper getConfigInfo()
    {
        return configInfo;
    }
    
    public void setConfigInfo(ConfigInfoMapper configInfo)
    {
        this.configInfo = configInfo;
    }
    
    public ChargeTypeMapper getChargeType()
    {
        return chargeType;
    }
    
    public void setChargeType(ChargeTypeMapper chargeType)
    {
        this.chargeType = chargeType;
    }
    
    public ServiceResponseMapper getServiceResponse()
    {
        return serviceResponse;
    }
    
    public void setServiceResponse(ServiceResponseMapper serviceResponse)
    {
        this.serviceResponse = serviceResponse;
    }
    
    public ContactInfoMapper getContactInfo()
    {
        return contactInfo;
    }
    
    public void setContactInfo(ContactInfoMapper contactInfo)
    {
        this.contactInfo = contactInfo;
    }
    
    public ExtendedAttributeMapper getExtendedAttribute()
    {
        return extendedAttribute;
    }
    
    public void setExtendedAttribute(ExtendedAttributeMapper extendedAttribute)
    {
        this.extendedAttribute = extendedAttribute;
    }
    
    public EnterpriseAddressMapper getEnterpriseAddress()
    {
        return enterpriseAddress;
    }
    
    public void setEnterpriseAddress(EnterpriseAddressMapper enterpriseAddress)
    {
        this.enterpriseAddress = enterpriseAddress;
    }
    
    public EnterpriseContactsMapper getEnterpriseContact()
    {
        return enterpriseContact;
    }
    
    public void setEnterpriseContact(EnterpriseContactsMapper enterpriseContact)
    {
        this.enterpriseContact = enterpriseContact;
    }
    
}