package com.wos.mgt;

import java.util.List;

import javax.jws.WebService;

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

@WebService
public interface InstallDocMgt
{
    
    /*
     * install document base info
     */
    //install document
    public InstallDocument loadInstallDocumentByEventCode(String eventCodeText);
    
    //template
    public List<InstallTemplate> getAllInstallTemplates();    
    public List<InstallDocuDetail> getInstallDetailByTemplate(
            String installTemplateText);
    
    //address info
    public List<EnterpriseAddress> getEnterpriseAddresses(
            String enterpriseIdText);    
    public Boolean addEnterpriseAddress(String addressText);    
    public Boolean updateEnterpriseAddress(String addressText);    
    public Boolean deleteEnterpriseAddress(String addressIdText);    
    public Boolean selectAsCurrentAddress(String currentAddressText);
    
    //tax organization
    public List<TaxOrganization> getAllTaxOrganizations();    
    public List<TaxOrganization> getTaxOrganizationsByParentCode(
            String parentCodeText);    
    public List<TaxOrganization> getTaxOrganizationsByName(String orgNameText);
    
    //contact info
    public List<ContactInfo> getEnterpriseContactInfo(String enterpriseIdText);    
    public ContactInfo getContactInfoById(String contactIdText);    
    public Boolean saveTelephone(String telephonesText);    
    public Boolean saveCellphone(String cellPhonesText);    
    public Boolean addContactInfo(String contactInfoText);    
    public Boolean deleteContactInfo(String contactIdText);    
    public Boolean updateContactInfo(String contactInfoText);    
    public Boolean selectAsCurrentContact(String currentContactText);
    
    /*
     * install document detail
     */
    //收费类型
    public List<ChargeType> loadAllChargeTypes();    
    //服务响应类型
    public List<ServiceResponse> loadAllServiceResponses();
    
    /*
     * extended attributes
     */
    public List<ExtendedAttribute> loadExtendedAttributesByMatId(
            String matIdText);
    
    /*
     * other
     */
    public List<ConfigInfo> loadAllInstallTypes();
    
}