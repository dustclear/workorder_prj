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
    public String loadInstallDocumentByEventCode(String eventCodeText);
    
    //template
    public String getAllInstallTemplates();    
    public String getInstallDetailByTemplate(
            String installTemplateText);
    
    //address info
    public String getEnterpriseAddresses(
            String enterpriseIdText);    
    public String addEnterpriseAddress(String addressText);    
    public String updateEnterpriseAddress(String addressText);    
    public String deleteEnterpriseAddress(String addressIdText);    
    public String selectAsCurrentAddress(String currentAddressText);
    
    //tax organization
    public String getAllTaxOrganizations();    
    public String getTaxOrganizationsByParentId(
            String parentIdText);    
    public String getTaxOrganizationsByName(String orgNameText);
    
    //contact info
    public String getEnterpriseContactInfo(String enterpriseIdText);    
    public String getContactInfoById(String contactIdText);    
    public String saveTelephone(String telephonesText);    
    public String saveCellphone(String cellPhonesText);    
    public String addContactInfo(String contactInfoText);    
    public String deleteContactInfo(String contactIdText);    
    public String updateContactInfo(String contactInfoText);    
    public String selectAsCurrentContact(String currentContactText);
    
    /*
     * install document detail
     */
    //收费类型
    public String loadAllChargeTypes();    
    //服务响应类型
    public String loadAllServiceResponses();
    
    /*
     * extended attributes
     */
    public String loadExtendedAttributesByMatId(
            String matIdText);
    
    /*
     * other
     */
    public String loadAllInstallTypes();
    
}