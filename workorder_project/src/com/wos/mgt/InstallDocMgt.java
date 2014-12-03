package com.wos.mgt;
 
import java.util.List;

import javax.jws.WebService;

import com.wos.pojo.ConfigInfo;
import com.wos.pojo.ContactInfo;
import com.wos.pojo.EnterpriseAddress;
import com.wos.pojo.EventInfo;
import com.wos.pojo.ExtendedAttribute;
import com.wos.pojo.TaxOrganization;
 
@WebService
public interface InstallDocMgt {
	public EventInfo loadEventByCode(String eventCodeText);
	
	//tax organization
	public List<TaxOrganization> getAllTaxOrganizations();
	public List<TaxOrganization> getTaxOrganizationsByParentCode(String parentCodeText);
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
	
	//address info
	public List<EnterpriseAddress> getEnterpriseAddresses(String enterpriseIdText);
	public Boolean addEnterpriseAddress(String addressText);
	public Boolean updateEnterpriseAddress(String addressText);
	public Boolean deleteEnterpriseAddress(String addressIdText);
	public Boolean selectAsCurrentAddress(String currentAddressText);
	
	//
	public List<ConfigInfo> loadAllInstallTypes();
	public List<ExtendedAttribute> loadAllExtendedAttrs();
	public Boolean saveExtendedAttrs(String extendedAttrText);
	
	
	
	
}