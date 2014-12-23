package com.wos.mgt.impl;

import java.util.List;

import javax.jws.WebService;

import com.google.gson.Gson;
import com.wos.common.WosHelper;
import com.wos.dao.mapper.AddressTypeMapper;
import com.wos.dao.mapper.ChargeTypeMapper;
import com.wos.dao.mapper.ConfigInfoMapper;
import com.wos.dao.mapper.ContactInfoMapper;
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
import com.wos.pojo.ExtendedAttribute;
import com.wos.pojo.InstallDocuDetail;
import com.wos.pojo.InstallDocument;
import com.wos.pojo.InstallTemplate;
import com.wos.pojo.ServiceResponse;
import com.wos.pojo.TaxOrganization;

@WebService(endpointInterface = "com.wos.mgt.InstallDocMgt")
public class InstallDocMgtImpl implements InstallDocMgt
{
    private AddressTypeMapper addressType;
    private InstallDocumentMapper installDocument;
    private InstallTemplateMapper installTemplate;
    private InstallDocuDetailMapper installDocuDetail;
    private TaxOrganizationMapper taxOrganization;
    private ConfigInfoMapper configInfo;
    private ChargeTypeMapper chargeType;
    private ServiceResponseMapper serviceResponse;
    private ContactInfoMapper contactInfo;
    
    private static final Gson _gson = new Gson();
    
    
    private WosHelper _helper = WosHelper.getInstance();
    
    @Override
    public String loadInstallDocumentByEventCode(String argEventCodeText)
    {
        String eventCode = _helper.getValueFromJsonText(argEventCodeText, "ceventid");
        
        InstallDocument doc = installDocument.findInstallDocumentByEventCode(eventCode);
        _helper.toJsonText(doc, InstallDocument.class);
        
        return _helper.toJsonText(doc, InstallDocument.class);
    }

    @Override
    public List<InstallTemplate> getAllInstallTemplates()
    {
        List<InstallTemplate> templates = installTemplate.loadAllInstallTemplates();
        _helper.toJsonText(templates, null);
        return null;
    }

    @Override
    public List<InstallDocuDetail> getInstallDetailByTemplate(
            String argInstallTemplateText)
    {
        String installTemplate = _helper.getValueFromJsonText(argInstallTemplateText, "installTemplate");
        List<InstallDocuDetail> installDocuDetails = installDocuDetail.findInstallDetailByTemplate(installTemplate);
        _helper.toJsonText(installDocuDetails, null);
        
        return null;
    }

    @Override
    public List<TaxOrganization> getAllTaxOrganizations()
    {
        List<TaxOrganization> taxOrganizations = taxOrganization.loadAllTaxOrganizations();
        _helper.toJsonText(taxOrganizations, null);
        return null;
    }

    @Override
    public List<TaxOrganization> getTaxOrganizationsByParentCode(
            String argParentCodeText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TaxOrganization> getTaxOrganizationsByName(String argOrgNameText)
    {
        String orgName = _helper.getValueFromJsonText(argOrgNameText, "cName");
        List<TaxOrganization> taxOrganizations = taxOrganization.findTaxOrganizationByName(orgName);
        _helper.toJsonText(taxOrganizations, null);
        return null;
    }

    @Override
    public List<ContactInfo> getEnterpriseContactInfo(String argEnterpriseIdText)
    {
        String enterpriseId = _helper.getValueFromJsonText(argEnterpriseIdText, "cEnterpriseID");
//        List<ContactInfo> contactInfos = _contactInfoMapper.findContactInfoByEnterpriseId(enterpriseId);
//        _helper.toJsonText(contactInfos, null);
        return null;
    }

    @Override
    public ContactInfo getContactInfoById(String argContactIdText)
    {
        String contactInfoId = _helper.getValueFromJsonText(argContactIdText, "cGUID");
//        List<ContactInfo> contactInfos = _contactInfoMapper.findContactInfoById(contactInfoId);
//        _helper.toJsonText(contactInfos.get(0), null);
        return null;
    }

    @Override
    public Boolean saveTelephone(String argTtelephonesText)
    {
        ContactInfo contactInfo =  _gson.fromJson(argTtelephonesText, ContactInfo.class);
        
//        Boolean result = _contactInfoMapper.updateTelephone(contactInfo);
        return null;
    }

    @Override
    public Boolean saveCellphone(String argCellPhonesText)
    {
        ContactInfo contactInfo =  _gson.fromJson(argCellPhonesText, ContactInfo.class);
        
//        Boolean result = _contactInfoMapper.updateCellphone(contactInfo);
        return null;
    }

    @Override
    public Boolean addContactInfo(String argContactInfoText)
    {
        ContactInfo contactInfoRes =  _gson.fromJson(argContactInfoText, ContactInfo.class);
        
        int result = contactInfo.insert(contactInfoRes);
        return null;
    }

    @Override
    public Boolean deleteContactInfo(String argContactIdText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updateContactInfo(String argContactInfoText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean selectAsCurrentContact(String argCurrentContactText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EnterpriseAddress> getEnterpriseAddresses(
            String argEnterpriseIdText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean addEnterpriseAddress(String argAddressText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updateEnterpriseAddress(String argAddressText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteEnterpriseAddress(String argAddressIdText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean selectAsCurrentAddress(String argCurrentAddressText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ConfigInfo> loadAllInstallTypes()
    {
        List<ConfigInfo> configInfos = configInfo.loadAllConfigInfos();
        _helper.toJsonText(configInfos, null);
        return null;
    }

    @Override
    public List<ChargeType> loadAllChargeTypes()
    {
        List<ChargeType> chargeTypes = chargeType.loadAllChargeTypes();
        _helper.toJsonText(chargeTypes, null);
        return null;
    }

    @Override
    public List<ServiceResponse> loadAllServiceResponses()
    {
        List<ServiceResponse> serviceResponses = serviceResponse.loadAllServiceResponses();
        _helper.toJsonText(serviceResponses, null);
        return null;
    }

    @Override
    public List<ExtendedAttribute> loadExtendedAttributesByMatId(
            String argMatIdText)
    {
        // TODO Auto-generated method stub
        return null;
    }

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
    
    
    
    
}