package com.wos.mgt.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wos.common.WosConstant;
import com.wos.common.WosHelper;
import com.wos.dao.mapper.AddressTypeMapper;
import com.wos.dao.mapper.AreaClassMapper;
import com.wos.dao.mapper.ChargeTypeMapper;
import com.wos.dao.mapper.ConfigInfoMapper;
import com.wos.dao.mapper.ContactInfoMapper;
import com.wos.dao.mapper.EnterpriseAddressMapper;
import com.wos.dao.mapper.EnterpriseBaseInfoMapper;
import com.wos.dao.mapper.EnterpriseContactsMapper;
import com.wos.dao.mapper.EventInfoMapper;
import com.wos.dao.mapper.ExtendedAttributeMapper;
import com.wos.dao.mapper.InstallDetailMapper;
import com.wos.dao.mapper.InstallDocuCofigMapper;
import com.wos.dao.mapper.InstallDocuDetailMapper;
import com.wos.dao.mapper.InstallDocumentMapper;
import com.wos.dao.mapper.InstallTemplateMapper;
import com.wos.dao.mapper.MaintainDocumentMapper;
import com.wos.dao.mapper.MaterialMapper;
import com.wos.dao.mapper.RmsUserMapper;
import com.wos.dao.mapper.ServiceResponseMapper;
import com.wos.dao.mapper.TaxOrganizationMapper;
import com.wos.mgt.InstallDocMgt;
import com.wos.pojo.AreaClass;
import com.wos.pojo.ChargeType;
import com.wos.pojo.ConfigInfo;
import com.wos.pojo.ContactInfo;
import com.wos.pojo.EnterpriseAddress;
import com.wos.pojo.EnterpriseBaseInfo;
import com.wos.pojo.EnterpriseContacts;
import com.wos.pojo.EventInfo;
import com.wos.pojo.ExtendedAttribute;
import com.wos.pojo.InstallDetail;
import com.wos.pojo.InstallDocuCofig;
import com.wos.pojo.InstallDocuDetail;
import com.wos.pojo.InstallDocument;
import com.wos.pojo.InstallTemplate;
import com.wos.pojo.MaintainDocument;
import com.wos.pojo.Material;
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
    
    private InstallDocuCofigMapper installDocuCofigMapper;
    
    private TaxOrganizationMapper taxOrganizationMapper;
    
    private ConfigInfoMapper configInfoMapper;
    
    private ChargeTypeMapper chargeTypeMapper;
    
    private ServiceResponseMapper serviceResponseMapper;
    
    private ContactInfoMapper contactInfoMapper;
    
    private ExtendedAttributeMapper extendedAttributeMapper;
    
    private EnterpriseAddressMapper enterpriseAddressMapper;
    
    private EnterpriseContactsMapper enterpriseContactMapper;
    
    private AreaClassMapper areaClassMapper;
    
    private MaterialMapper materialMapper;
    
    private EnterpriseBaseInfoMapper enterpriseBaseInfoMapper;
    
    private MaintainDocumentMapper maintainDocumentMapper;
    
    private static final Gson _gson = new GsonBuilder().serializeNulls()
            .setDateFormat(WosConstant.DATE_TIME_FORMAT)
            .create();
    
    private WosHelper _helper = WosHelper.getInstance();
    
    private RmsUser currentUser;
    
    private InstallDocument currentInstallDocument;
    
    private MaintainDocument currentMaintainDocument;
    
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
        return _helper.toJsonText(doc, InstallDocument.class);
    }
    
    @Override
    public String createAnEmptyInstallDocument()
    {
        InstallDocument newEmptyInstallDocument = new InstallDocument();
        newEmptyInstallDocument.setCguid(_helper.generatePrimaryKey());
        // 纸质单号
        newEmptyInstallDocument.setCcode(_helper.generateInstallCode());
        
        return _helper.toJsonText(newEmptyInstallDocument,
                InstallDocument.class);
    }
    
    
    
    @Override
    public String loadMaintainDocumentByEventCode(String argEventCodeText)
    {
        String eventCode = _helper.getValueFromJsonText(argEventCodeText,
                "ccode");
        String userId = _helper.getValueFromJsonText(argEventCodeText, "cguid"); // 此处的cguid为userid，对应表AOS_RMS_USER的cguid
        
        if (StringUtils.isNotEmpty(userId))
        {
            currentUser = rmsUserMapper.findUserById(userId);
        }

        EventInfo eventInfo = eventInfoMapper.loadEventInfoByEventCode(eventCode);
        MaintainDocument doc = createMaintainDocumentFromEventInfo(eventInfo);
        
        return _helper.toJsonText(doc, MaintainDocument.class);
    }
    
    
    @Override
    public String createAnEmptyMaintainDocument()
    {
        MaintainDocument newMaintainDocument = new MaintainDocument();
        newMaintainDocument.setCguid(_helper.generatePrimaryKey());
        // 纸质单号
        newMaintainDocument.setCcode(_helper.generateInstallCode());
        
        return _helper.toJsonText(newMaintainDocument,
                MaintainDocument.class);
    }
    
    @Override
    public String saveMaintainDocument(String argMaintainDocumentText)
    {
        MaintainDocument newMaintainDocument = _gson.fromJson(argMaintainDocumentText,
                MaintainDocument.class);
        
        newMaintainDocument.setCcreatedate(new Date());
        int result = maintainDocumentMapper.insertSelective(newMaintainDocument);
        return _helper.toJsonText(result, null);
    }

    @Override
    public String getEnterpriseInfoByName(String nameText)
    {
        String name = _helper.getValueFromJsonText(nameText, "cname");
        List<EnterpriseBaseInfo> enterpriseBaseInfos = enterpriseBaseInfoMapper.findEnterpriseInfoByName(name);
        return _helper.toJsonText(enterpriseBaseInfos, null);
    }
    
    @Override
    public String getEnterpriseInfoByTaxCode(String taxCodeText)
    {
        String taxCode = _helper.getValueFromJsonText(taxCodeText, "ctaxcode");
        EnterpriseBaseInfo enterpriseBaseInfo = enterpriseBaseInfoMapper.findEnterpriseInfoByTaxCode(taxCode);
        return _helper.toJsonText(enterpriseBaseInfo, null);
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
        String installDocumentId = _helper.getValueFromJsonText(argInstallTemplateText,
                "installDocumentId");
        
        List<InstallDetail> installDetails = installDetailMapper.findInstallDetailByTemplateId(installTemplateId);
        
        List<InstallDocuDetail> installDocuDetails = createInstallDocDetailsFromInstallDetail(installDetails,
                installDocumentId);
        
        return _helper.toJsonText(installDocuDetails, null);
    }
    
    @Override
    public String getAllTaxOrganizations()
    {
        List<TaxOrganization> taxOrganizations = taxOrganizationMapper.loadAllTaxOrganizations();
        return _helper.toJsonText(taxOrganizations, null);
    }
    
    @Override
    public String getAllAreaInfo()
    {
        List<AreaClass> areaClasses = areaClassMapper.findAllAreaClasses();
        return _helper.toJsonText(areaClasses, null);
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
    public String isContactInfoExist(String argContactInfoText)
    {
        ContactInfo contactInfoParam = _gson.fromJson(argContactInfoText,
                ContactInfo.class);
        
        ContactInfo existContactInfo = contactInfoMapper.findContactInfoByNameAndPhone(contactInfoParam);
        
        return _helper.toJsonText(existContactInfo, null);
    }
    
    @Override
    public String addContactInfoToEnterprise(String argContactInfoText)
    {
        int result = 0;
        ContactInfo contactInfoParam = _gson.fromJson(argContactInfoText,
                ContactInfo.class);
        
        ContactInfo existContactInfo = contactInfoMapper.findContactInfoByNameAndPhone(contactInfoParam);
        if (existContactInfo != null)
        {
            Map<String, String> mapRes = _gson.fromJson(argContactInfoText,
                    new TypeToken<Map<String, String>>()
                    {
                    }.getType());
            
            EnterpriseContacts enterpriseContact = new EnterpriseContacts();
            enterpriseContact.setCguid(_helper.generatePrimaryKey());
            enterpriseContact.setCcontactid(existContactInfo.getCguid());
            if (mapRes != null)
            {
                enterpriseContact.setCenterpriseid(mapRes.get("centerpriseid"));
                if (mapRes.get("cisonjob") != null)
                {
                    enterpriseContact.setCisonjob(Integer.valueOf(mapRes.get("cisonjob")));
                }
                if (mapRes.get("isnew") != null)
                {
                    enterpriseContact.setIsnew(Short.valueOf(mapRes.get("isnew")));
                }
                
            }
            
            result = enterpriseContactMapper.insertSelective(enterpriseContact);
        }
        
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String addContactInfo(String argContactInfoText)
    {
        ContactInfo contactInfoRes = _gson.fromJson(argContactInfoText,
                ContactInfo.class);
        if (contactInfoRes != null)
        {
            Map<String, String> mapRes = _gson.fromJson(argContactInfoText,
                    new TypeToken<Map<String, String>>()
                    {
                    }.getType());
            
            String baseContactId = _helper.generatePrimaryKey();
            contactInfoRes.setCguid(baseContactId);
            
            EnterpriseContacts enterpriseContact = new EnterpriseContacts();
            enterpriseContact.setCguid(_helper.generatePrimaryKey());
            enterpriseContact.setCcontactid(baseContactId);
            if (mapRes != null)
            {
                enterpriseContact.setCenterpriseid(mapRes.get("centerpriseid"));
                if (mapRes.get("cisonjob") != null)
                {
                    enterpriseContact.setCisonjob(Integer.valueOf(mapRes.get("cisonjob")));
                }
                if (mapRes.get("isnew") != null)
                {
                    enterpriseContact.setIsnew(Short.valueOf(mapRes.get("isnew")));
                }
                
            }
            
            enterpriseContactMapper.insertSelective(enterpriseContact);
            int result = contactInfoMapper.insertSelective(contactInfoRes);
            return _helper.toJsonText(result, null);
        }
        return null;
    }
    
    @Override
    public String deleteContactInfo(String argContactIdText)
    {
        String cguid = _helper.getValueFromJsonText(argContactIdText, "cguid");
        int result = contactInfoMapper.deleteByPrimaryKey(cguid);
        // delete from enterprise contact table too
        enterpriseContactMapper.deleteByContactId(cguid);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String updateContactInfo(String argContactInfoText)
    {
        ContactInfo contactInfoUpdate = _gson.fromJson(argContactInfoText,
                ContactInfo.class);
        
        if (contactInfoUpdate != null)
        {
            int result = -1;
            try
            {
                result = contactInfoMapper.updateContactInfo(contactInfoUpdate);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
            // update enterprise contact info too
            String isOnJob = _helper.getValueFromJsonText(argContactInfoText,
                    "cisonjob");
            if (StringUtils.isNotBlank(isOnJob)
                    && StringUtils.isNotBlank(contactInfoUpdate.getCguid()))
            {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("ccontactid", contactInfoUpdate.getCguid());
                params.put("isOnJob", Integer.valueOf(isOnJob));
                enterpriseContactMapper.updateJobStatus(params);
                
            }
            return _helper.toJsonText(result, null);
        }
        return null;
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
    public String isInstallDocDetailCodeExist(String installDetailText)
    {
        InstallDocuDetail detailRes = null;
        Type type = new TypeToken<ArrayList<InstallDocuDetail>>()
        {
        }.getType();
        List<InstallDocuDetail> docuDetaillist = _gson.fromJson(installDetailText,
                type);
        for (InstallDocuDetail installDocuDetail : docuDetaillist)
        {
            if (StringUtils.isBlank(installDocuDetail.getCcode()))
            {
                continue;
            }
            detailRes = installDocuDetailMapper.findInstallDetailByCode(installDocuDetail.getCcode());
            if (detailRes!= null)
            {
                break;
            }
            
        }
        
        return _helper.toJsonText(detailRes, null);
    }

    @Override
    public String saveInstallDocDetail(String installDetailText)
    {
        int result = 0;
        Type type = new TypeToken<ArrayList<InstallDocuDetail>>()
        {
        }.getType();
        List<InstallDocuDetail> docuDetaillist = _gson.fromJson(installDetailText,
                type);
        for (InstallDocuDetail installDocuDetail : docuDetaillist)
        {
            if (StringUtils.isBlank(installDocuDetail.getCguid()))
            {
                installDocuDetail.setCguid(_helper.generatePrimaryKey());
            }
            result = installDocuDetailMapper.insertSelective(installDocuDetail);
            if (installDocuDetail.getInstallDocuCofigs() != null)
            {
                for (InstallDocuCofig installDocuCofig : installDocuDetail.getInstallDocuCofigs())
                {
                    if (StringUtils.isBlank(installDocuCofig.getCguid()))
                    {
                        installDocuCofig.setCguid(_helper.generatePrimaryKey());
                    }
                    installDocuCofigMapper.insertSelective(installDocuCofig);
                }
            }
            
        }
        
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
        
        installDocumentUpdate.setCcreatedate(new Date());
        int result = installDocumentMapper.insertSelective(installDocumentUpdate);
        return _helper.toJsonText(result, null);
    }
    
    @Override
    public String loadMaterialByCodeOrName(String codeOrNameText)
    {
        String codeOrName = _helper.getValueFromJsonText(codeOrNameText,
                "codeOrName");
        
        List<Material> materials = materialMapper.getMaterialByCodeOrName(codeOrName);
        
        return _helper.toJsonText(materials, null);
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
    
    public AreaClassMapper getAreaClassMapper()
    {
        return areaClassMapper;
    }
    
    public void setAreaClassMapper(AreaClassMapper areaClassMapper)
    {
        this.areaClassMapper = areaClassMapper;
    }
    
    public InstallDocuCofigMapper getInstallDocuCofigMapper()
    {
        return installDocuCofigMapper;
    }
    
    public void setInstallDocuCofigMapper(
            InstallDocuCofigMapper installDocuCofigMapper)
    {
        this.installDocuCofigMapper = installDocuCofigMapper;
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
                newInstallDocument.setCcode(_helper.generateInstallCode());
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
    
    private MaintainDocument createMaintainDocumentFromEventInfo(
            EventInfo eventInfo)
    {
        MaintainDocument newMaintainDocument = null;
        if (eventInfo != null)
        {
            try
            {
                newMaintainDocument = new MaintainDocument();
                newMaintainDocument.setCguid(_helper.generatePrimaryKey());
                
                newMaintainDocument.setCeventid(eventInfo.getCguid());
                // 纸质单号
                newMaintainDocument.setCcode(_helper.generateInstallCode());
                // 企业信息
                newMaintainDocument.setEnterpriseBaseInfo(eventInfo.getEnterpriseBaseInfo());
                // 企业信息快照
                newMaintainDocument.setCenterpriseid(eventInfo.getCenterpriseid());
                newMaintainDocument.setCenterprisename(eventInfo.getCenterprisename());
                newMaintainDocument.setCtaxcode(eventInfo.getCtaxcode());
                
                newMaintainDocument.setCenterpriseadress(eventInfo.getCenterpriseadress());
                newMaintainDocument.setCenterprisedepartment(null);
                newMaintainDocument.setCcontactid(eventInfo.getCcontactid());
                newMaintainDocument.setCcontactname(eventInfo.getCcontactname());
                newMaintainDocument.setCcontacttel(eventInfo.getCcontacttel());
                newMaintainDocument.setCcontactphone(eventInfo.getCcontactphone());
                newMaintainDocument.setCarea(eventInfo.getAreaClass().getCname());
                
                if (currentUser != null && currentUser.getEmployee() != null)
                {
                    newMaintainDocument.setEmployee(currentUser.getEmployee());
                    newMaintainDocument.setCemployeeid(currentUser.getEmployee()
                            .getCguid());
                    if (currentUser.getEmployee().getDepartment() != null)
                    {
                        newMaintainDocument.setCdepartment(currentUser.getEmployee()
                                .getDepartment()
                                .getCname());
                    }
                    
                }
                
                if (eventInfo.getEnterpriseBaseInfo() != null
                        && eventInfo.getEnterpriseBaseInfo()
                                .getCurrentTaxOrganization() != null)
                {
                    newMaintainDocument.setCbelongtax(eventInfo.getEnterpriseBaseInfo()
                            .getCurrentTaxOrganization()
                            .getCguid());
                }
                
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
        }
        
        return newMaintainDocument;
    }
    
    private List<InstallDocuDetail> createInstallDocDetailsFromInstallDetail(
            List<InstallDetail> argInstallDetails, String installDocumentId)
    {
        List<InstallDocuDetail> installDocuDetails = new ArrayList<InstallDocuDetail>();
        
        for (InstallDetail installDetail : argInstallDetails)
        {
            try
            {
                InstallDocuDetail newInstallDocuDetail = new InstallDocuDetail();
                newInstallDocuDetail.setCguid(_helper.generatePrimaryKey());
                
                createDetailCode(newInstallDocuDetail, installDetail);
                if (installDetail.getMaterial() != null)
                {
                    newInstallDocuDetail.setCname(installDetail.getMaterial()
                            .getCname());
                    newInstallDocuDetail.setCversion(installDetail.getMaterial()
                            .getCspec());
                }
                
                newInstallDocuDetail.setCrelationmatid(installDetail.getCmatid());
                newInstallDocuDetail.setRelateMaterial(installDetail.getMaterial());
                
                newInstallDocuDetail.setCinstalltypeid(installDetail.getCinstalltypeid());
                newInstallDocuDetail.setInstallType(installDetail.getInstallType());
                
                newInstallDocuDetail.setCismain(installDetail.getCismain());
                newInstallDocuDetail.setCisstatus(1);
                newInstallDocuDetail.setCchargetypeid(installDetail.getCchargetype());
                newInstallDocuDetail.setChargeType(installDetail.getChargeType());
                newInstallDocuDetail.setCresponseid(installDetail.getCresponseid());
                newInstallDocuDetail.setServiceResponse(installDetail.getServiceResponse());
                newInstallDocuDetail.setCguaranteestartdate(new Date());
                newInstallDocuDetail.setCguaranteeenddate(new Date());
                newInstallDocuDetail.setCisinput(installDetail.getCisinput());
                
                /*if (currentInstallDocument != null)
                {
                    newInstallDocuDetail.setCmainid(currentInstallDocument.getCguid());
                    newInstallDocuDetail.setCcontactid(currentInstallDocument.getCcontactid());
                    if (currentInstallDocument.getEnterpriseBaseInfo() != null)
                    {
                        newInstallDocuDetail.setContactInfo(currentInstallDocument.getEnterpriseBaseInfo()
                                .getCurrentContact());
                    }
                }
                else
                {*/
                    newInstallDocuDetail.setCmainid(installDocumentId);
//                }
                // 创建扩展属性
                createExtendedAttrsForInstallDocDetail(newInstallDocuDetail);
                //初始化服务开始和结束时间
                initialServiceDateOfInstallDetail(newInstallDocuDetail);
                
                installDocuDetails.add(newInstallDocuDetail);
                
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
        }
        
        return installDocuDetails;
    }
    
    /**
     *                                  <value name="0">年<alue>
     *
     *                                  <value name="1">月<alue>
     *                                  
     *                                  <value name="2">日<alue>
     *                                  
     *                                  <value name="3">次<alue>
     * @param installDocuDetail
     */
    private void initialServiceDateOfInstallDetail(
            InstallDocuDetail installDocuDetail)
    {
        Date startDate = new Date();
        Date endDate = new Date();
        
        ChargeType chargeType = installDocuDetail.getChargeType();
        if (chargeType != null)
        {
            String periodUnitText = chargeType.getCperiodsunit();
            Integer freeQty = chargeType.getFreecharge();
            if (StringUtils.isNotBlank(periodUnitText) && freeQty != null)
            {
                try
                {
                    int periodUnit = Integer.valueOf(periodUnitText);
                    switch (periodUnit)
                    {
                        case 0:
                            endDate = DateUtils.addYears(startDate, freeQty);
                            break;
                        case 1:
                            endDate = DateUtils.addMonths(startDate, freeQty);
                            break;
                        case 2:
                            endDate = DateUtils.addDays(startDate, freeQty);
                            break;
                        default:
                            break;
                    }
                    
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        installDocuDetail.setCservicestartdate(startDate);
        installDocuDetail.setCservicedata(endDate);
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
    
    private void createDetailCode(InstallDocuDetail docuDetail,
            InstallDetail detail)
    {
        String matId = detail.getCmatid();
        if (StringUtils.isNotBlank(matId)
                && (("247902721710531367".equals(matId)
                        || ("247902721710531372").equals(matId)
                        || ("132433004857549195").equals(matId) || ("132433004857549462").equals(matId))))
        {
            docuDetail.setCcode("");
        }
        else
        {
            docuDetail.setCcode(_helper.generateInstallDetailCode());
        }
    }
    
    public MaterialMapper getMaterialMapper()
    {
        return materialMapper;
    }
    
    public void setMaterialMapper(MaterialMapper materialMapper)
    {
        this.materialMapper = materialMapper;
    }
    
    public EnterpriseBaseInfoMapper getEnterpriseBaseInfoMapper()
    {
        return enterpriseBaseInfoMapper;
    }
    
    public void setEnterpriseBaseInfoMapper(
            EnterpriseBaseInfoMapper enterpriseBaseInfoMapper)
    {
        this.enterpriseBaseInfoMapper = enterpriseBaseInfoMapper;
    }

    public MaintainDocumentMapper getMaintainDocumentMapper()
    {
        return maintainDocumentMapper;
    }

    public void setMaintainDocumentMapper(
            MaintainDocumentMapper maintainDocumentMapper)
    {
        this.maintainDocumentMapper = maintainDocumentMapper;
    }
    
    
    
}