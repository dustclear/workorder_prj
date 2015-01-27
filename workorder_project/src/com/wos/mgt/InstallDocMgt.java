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

/**
 * 安装单管理API，业务逻辑：客户输入事件编码，加载出企业基本信息，然后选择安装单模板，生成一份新的安装单，同一个事件编码可以生成多个安装单，每次生成的都是新安装单（生成新的纸质单号）<br>
 * 用户点击保存后，会将当前安装单的信息保存到数据库表中，主表：itsm_service_InstallDocument，子表：itsm_service_InstallDocuDetail， itsm_service_InstallDocuCofing
 * @author machenike
 *
 */
@WebService
public interface InstallDocMgt
{
    
    /*
     * install document base info
     */
    /**
     * 根据事件编码生成一张空的安装单信息。 此处的cguid对应表AOS_RMS_USER的cguid， ccode对应表itsm_service_EventManagement的cCode，就是事件编码
     * @param eventCodeText： cguid和ccode。
     * @return string 注意输出结果，这是一个复杂的数据结构，包含：installdocument~enterpriseBaseInfo以及department，area等信息,前端解析时请注意。。
     */
    public String loadInstallDocumentByEventCode(String eventCodeText);
    
    /**
     * 创建一份空安装单，当用户没有加载事件编码时也可以输入安装单信息，
     * @return
     */
    public String createAnEmptyInstallDocument();
    
    /*
     * maintain document
     */
    /**
     * 根据事件编码生成一张空的维护单信息（主要是根据事件编码查询出企业信息）。 此处的cguid对应表AOS_RMS_USER的cguid， ccode对应表itsm_service_EventManagement的cCode，就是事件编码
     * @param eventCodeText： cguid和ccode。
     * @return string
     * @param eventCodeText
     * @return
     */
    public String loadMaintainDocumentByEventCode(String eventCodeText);
    
    /**
     * 创建一份空维护单，当用户没有加载事件编码时也可以输入维护单信息，
     * @return
     */
    public String createAnEmptyMaintainDocument();
    
    /**
     * 保存维护单信息
     * @param argMaintainDocumentText
     * @return
     */
    public String saveMaintainDocument(String argMaintainDocumentText);
    
    //enterpriseInfo
    
    /**
     * 根据企业名称查询出企业信息 参数：cname
     * @return
     */
    public String getEnterpriseInfoByName(String nameText);
    
    /**
     * 根据税号查询企业信息 参数：ctaxcode
     * @return
     */
    public String getEnterpriseInfoByTaxCode(String taxCodeText);
    
    
    //template
    /**
     * 加载所有的安装单模板到下拉框
     * @return
     */
    public String getAllInstallTemplates();    
    
    /**
     * 选择“管理软件安装单模板”后加载相应的配置项信息。传人参数为安装单模板id:installTemplateId, 之前获得的安装单id：installDocumentId
     * @param installTemplateText
     * @return
     */
    public String getInstallDetailByTemplate(
            String installTemplateText);
    
    //address info
    
    /**
     * 获取企业所有地址信息
     * @param enterpriseIdText
     * @return
     */
    public String getEnterpriseAddresses(
            String enterpriseIdText);   
    
    /**
     * 加载所有的区域信息
     * @return
     */
    public String getAllAreaInfo();   
    
    /**
     * 新增企业地址信息
     * @param addressText
     * @return
     */
    public String addEnterpriseAddress(String addressText);    
    
    /**
     * 修改企业地址信息
     * @param addressText
     * @return
     */
    public String updateEnterpriseAddress(String addressText);   
    
    /**
     * 删除一条企业地址信息
     * @param addressIdText
     * @return
     */
    public String deleteEnterpriseAddress(String addressIdText);    
    
    /**
     * 选择一条地址信息作为当前企业地址
     * @param currentAddressText
     * @return
     */
    public String selectAsCurrentAddress(String currentAddressText);
    
    //tax organization
    /**
     * 获取所有税务机关信息
     * @return
     */
    public String getAllTaxOrganizations();    
    
    /**
     * 根据上级主管信息查询税务机关信息
     * @param parentIdText
     * @return
     */
    public String getTaxOrganizationsByParentId(
            String parentIdText);    
    /**
     * 根据机关名称查询税务机关信息
     * @param orgNameText
     * @return
     */
    public String getTaxOrganizationsByName(String orgNameText);
    
    //contact info
    /**
     * 根据企业id查询联系人信息
     * @param enterpriseIdText
     * @return
     */
    public String getEnterpriseContactInfo(String enterpriseIdText);  
    
    /**
     * 根据主键查询基本联系人信息
     * @param contactIdText
     * @return
     */
    public String getContactInfoById(String contactIdText);   
    
    /**
     * 保存联系人的座机号码
     * @param telephonesText
     * @return
     */
    public String saveTelephone(String telephonesText);    
    
    /**
     * 保存联系人的手机号。
     * @param cellPhonesText
     * @return
     */
    public String saveCellphone(String cellPhonesText);  
    
    /**
     * 判断企业联系人信息是否已存在数据库中，姓名及手机或座机号相同即表示已存在，若已存在，弹出对话框问是否只将现有联系人关联到企业而不新增联系人。是：关联；否：新增并关联
     * 参数：ContactInfo，结构参考table：itsm_base_Contact
     * @param contactInfoText
     * @return 存在返回contact对象，不存在返回null
     */
    public String isContactInfoExist(String contactInfoText);   
    
    /**
     * 将现有的联系人关联到企业。不新增联系人信息
     * @param contactInfoText
     * @return
     */
    public String addContactInfoToEnterprise(String contactInfoText);  
    
    /**
     * 新增一条联系人信息，并关联到企业
     * @param contactInfoText
     * @return
     */
    public String addContactInfo(String contactInfoText);    
    
    /**
     * 删除一条联系人信息
     * @param contactIdText
     * @return
     */
    public String deleteContactInfo(String contactIdText);    
    
    /**
     * 更新一条联系人信息
     * @param contactInfoText
     * @return
     */
    public String updateContactInfo(String contactInfoText);    
    
    /**
     * 选择一位联系人作为当前企业联系人
     * @param currentContactText
     * @return
     */
    public String selectAsCurrentContact(String currentContactText);
    
    /**
     * 加载所有收费类型
     * @return
     */
    public String loadAllChargeTypes();    
    //服务响应类型
    /**
     * 加载所有服务响应类型
     * @return
     */
    public String loadAllServiceResponses();
    
    /**
     * 在调用saveInstallDocDetail保存配置项前必须调用此方法，判断配置项的编码是否和数据库里的数据有重复。
     * 输入参数同saveInstallDocDetail， 返回：若code重复，返回数据库里的重复数据，若不重复，返回null。
     * @param installDetailText
     * @return
     */
    public String isInstallDocDetailCodeExist(String installDetailText);
    
    /**
     * 保存配置项信息的修改
     * @param installDetailText
     * @return
     */
    public String saveInstallDocDetail(String installDetailText);
    
    /**
     * 新增一条配置项信息
     * @param installDetailText
     * @return
     */
    @Deprecated
    public String addInstallDocDetail(String installDetailText);
    
    /**
     * 根据配置项ID查询出其扩展属性信息
     * @param matIdText
     * @return
     */
    public String loadExtendedAttributesByMatId(
            String matIdText);
    
    public String loadMaterialByCodeOrName(String codeOrNameText);
    
    /*
     * other
     */
    /**
     * 加载所有的安装类型
     * @return
     */
    public String loadAllInstallTypes();
    
    /**
     * 保存当前安装单信息的修改
     * @param installDocumentText
     * @return
     */
    public String saveInstallDocument(String installDocumentText);
    
}