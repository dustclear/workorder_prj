package com.wos.mgt.impl;

import java.util.List;

import javax.jws.WebService;

import com.wos.common.WosHelper;
import com.wos.dao.mapper.AddressTypeMapper;
import com.wos.dao.mapper.InstallDocumentMapper;
import com.wos.dao.mapper.InstallTemplateMapper;
import com.wos.mgt.InstallDocMgt;
import com.wos.pojo.ConfigInfo;
import com.wos.pojo.ContactInfo;
import com.wos.pojo.EnterpriseAddress;
import com.wos.pojo.ExtendedAttribute;
import com.wos.pojo.InstallDocuDetail;
import com.wos.pojo.InstallDocument;
import com.wos.pojo.InstallTemplate;
import com.wos.pojo.TaxOrganization;

@WebService(endpointInterface = "com.wos.mgt.HelloWorld")
public class InstallDocMgtImpl implements InstallDocMgt
{
    private AddressTypeMapper _addressType;
    private InstallDocumentMapper _installDocumentMapper;
    private InstallTemplateMapper _insInstallTemplateMapper;
    
    private WosHelper _helper = WosHelper.getInstance();
    
    /*public String sayHi(String text)
    {
        AddressType type = addressType.getAllAddressTypes("zcdz").get(0);
        Gson gson = new Gson();
        System.out.println("sayHi called" + type.getCname()+" "+type.getCcode());
        return gson.toJson(type);
    }*/
    

    @Override
    public InstallDocument loadInstallDocumentByEventCode(String eventCodeText)
    {
        String eventCode = _helper.getValueFromJsonText(eventCodeText, "ceventid");
        
        InstallDocument doc = _installDocumentMapper.findInstallDocumentByEventCode(eventCode);
        _helper.toJsonText(doc, InstallDocument.class);
        
        return null;
    }

    @Override
    public List<InstallTemplate> getAllInstallTemplates()
    {
        List<InstallTemplate> templates = _insInstallTemplateMapper.loadAllInstallTemplates();
        _helper.toJsonText(templates, null);
        return null;
    }

    @Override
    public List<InstallDocuDetail> selectInstallTemplate(
            String installTemplateText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TaxOrganization> getAllTaxOrganizations()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TaxOrganization> getTaxOrganizationsByParentCode(
            String parentCodeText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TaxOrganization> getTaxOrganizationsByName(String orgNameText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ContactInfo> getEnterpriseContactInfo(String enterpriseIdText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ContactInfo getContactInfoById(String contactIdText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean saveTelephone(String telephonesText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean saveCellphone(String cellPhonesText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean addContactInfo(String contactInfoText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteContactInfo(String contactIdText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updateContactInfo(String contactInfoText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean selectAsCurrentContact(String currentContactText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EnterpriseAddress> getEnterpriseAddresses(
            String enterpriseIdText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean addEnterpriseAddress(String addressText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updateEnterpriseAddress(String addressText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteEnterpriseAddress(String addressIdText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean selectAsCurrentAddress(String currentAddressText)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ConfigInfo> loadAllInstallTypes()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ExtendedAttribute> loadAllExtendedAttrs()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean saveExtendedAttrs(String extendedAttrText)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    

    
    
    
    
    

    public AddressTypeMapper getAddressType()
    {
        return _addressType;
    }

    public void setAddressType(AddressTypeMapper addressType)
    {
        this._addressType = addressType;
    }
    
    
}