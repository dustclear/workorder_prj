package com.wos.test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wos.common.WosConstant;
import com.wos.common.WosHelper;
import com.wos.mgt.InstallDocMgt;
import com.wos.pojo.InstallDocuDetail;
import com.wos.pojo.InstallDocument;

public class TestInstallDocMgt
{
    private static InstallDocMgt service;
    private WosHelper _helper = WosHelper.getInstance();
    
    private static final Gson _gson = new GsonBuilder().serializeNulls().setDateFormat(WosConstant.DATE_TIME_FORMAT)
            .create();
    
    @BeforeClass
    public static void init()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext-*.xml");
        service = (InstallDocMgt)context.getBean("installDoc");
    }
    
    @Test
    public void testLoadInstallDocumentByEventCode() { 
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("ccode", "1312070104");
        map.put("cguid", "145293406198134209");
        String eventCode = _helper.toJsonText(map, null);
        
        String resStr=service.loadInstallDocumentByEventCode(eventCode);
        
        InstallDocument installDocumentUpdate = _gson.fromJson(resStr,
                InstallDocument.class);
        
        System.out.println(installDocumentUpdate);
        
        //注意resStr的输出结果，这是一个复杂的数据结构，包含：installdocument~enterpriseBaseInfo以及department，area等信息,前端解析时请注意。。
        System.out.println(eventCode); //{"cguid":"145293406198134209","ccode":"1312070104"}
        System.out.println(resStr);    //{"cguid":"222676258641485788","ceventid":"158098265383583058","ccode":"mobile099817320773","centerpriseid":"20121122110027770","ctaxcode":"321600608254201","centerprisename":"张家港保税区华祥纺织印染国际贸易有限公司","cbelongtax":null,"centerpriseadress":"张家港杨舍镇向阳新村33栋202","centerprisedepartment":null,"ccontactid":"158098265383583010","ccontactname":"陆卫芳","ccontacttel":"58155580","ccontactphone":"18962270770","cinstalltypeid":null,"cinstalldate":null,"cinstallresult":null,"cunsuccessresult":null,"cdepartment":"服务部","carea":"张家港保税区","cemployeeid":"145246972521717801","cstatus":null,"ccreater":null,"ccreatedate":null,"cauditorguid":null,"caudittime":null,"clastupdater":null,"clastupdatedate":null,"ctimestamp":null,"cinstalltype":null,"remarks":null,"isauditingqualified":null,"isauditingeffective":null,"noqualifiedreason":null,"customersignature":null,"enterpriseBaseInfo":{"cguid":"20121122110027770","ccode":"1","cname":"苏州丽航国际货运代理有限公司","cfastcode":"szlhgjhydlyxgs","ctypeid":"000000000000000001","clegalrepresentative":null,"ctel1":"68322575","ctel2":null,"ctel3":null,"ctel4":null,"ctel5":null,"cfax1":null,"cfax2":null,"cfax3":null,"cfax4":null,"cfax5":null,"ctaxcode":"320500692553043","careaid":"705106892172137892","ctaxationid":"2035","cregisteredaddress":"苏州市珠江南路378号天隆大厦447","cwebsite":null,"cindustryid":null,"cregisteredcapital":null,"cfoundingtime":null,"cstaffs":null,"centerprisepropertyid":null,"cbusinessscope":null,"cservicetelephone":null,"cmembercard":null,"cisoverdue":0,"cinvoiceamount":null,"cinvoicingamount":null,"ccreater":"1","ccreatedate":"2013-12-06 23:24:44","cupdater":null,"cupdatedate":null,"ctimestamp":null,"cfastcode1":null,"crmid":"9560723","currentAddress":null,"currentContact":null,"currentTaxOrganization":null},"employee":{"cguid":"145246972521717801","ccode":"577","cname":"张黎","csname":null,"cdeptguid":"10","ctimestamp":"145246972521717800","istatus":1,"ileaf":1,"cparentid":"000000","isex":"0","djoinworkdate":null,"cjoinsysdate":null,"cidnumber":null,"iforeigner":0,"istoppayment":0,"idimission":0,"cdimissiondate":null,"iisdeleted":null,"cemail":null,"cmobile":"18013166773","cofficephone":null,"cemployeetypeguid":null,"chotcode":"zl","ccompguid":null,"cgroupguid":null,"cinsurance":null,"cposition":null,"ieducation":null,"ipolitical":null,"dbirthday":null,"dstartdate":null,"denddate":null,"chomeaddress":null,"csummy":null,"chousingfund":null,"cspare":null,"crmid":"296553","department":{"csname":"服务部","cname":"服务部","ccode":"fwb","cguid":"10","ileaf":1,"cparentid":"7","ctimestamp":null,"istatus":1,"cpathcode":"qyglrjsyb!fwb","ilevel":1,"chotcode":null,"ccompguid":"1","cfullcode":"qyglrjsyb!fwb","cinnercode":"qyglrjsyb!fwb","cemail":null,"cdeptprincipalguid":null,"cdeptprincipalguid2":null,"cdeptprincipalguid3":null,"cdeptdirectorguid":null,"cdeptdirectorguid2":null,"cdeptdirectorguid3":null,"crmid":"39657"}}}
    }
    
    @Test
    public void testGetAllTaxOrganizations()
    {
        String resStr=service.getAllTaxOrganizations();
        System.out.println(resStr);     //[{"cguid":"165120663464490419","ccode":"szsguqgffj","cname":"苏州市姑苏区国税分局","corganizationcode":"1","cparentid":"2001","cinnercode":"jssgjswj!jssszsgjswj!szsguqgffj","ileaf":1,"ccreater":"1","ccreatedate":"Dec 9, 2013 3:11:52 PM","ctimestamp":"165120663464490418"},{"cguid":"102102","ccode":"szsdefj","cname":"苏州市第二国税分局","corganizationcode":"111","cparentid":"2001","cinnercode":"jssgjswj!jssszsgjswj!szsdefj","ileaf":1,"ccreater":"1","ccreatedate":"Dec 25, 2013 2:54:55 PM","ctimestamp":"102780964199954128","cfullname":"苏州市第二分局"},{"cguid":"920623803615892447","ccode":"00001","cname":"其他","corganizationcode":"0001","cparentid":"000000","cinnercode":"00001","ileaf":1,"ccreater":"1","ccreatedate":"Jan 17, 2014 2:16:27 PM","ctimestamp":"920623803615892446","cfullname":"其他"},{"cguid":"8329","ccode":"tcsgslfj","cname":"太仓市国税六分局","cparentid":"2032","cinnercode":"jssgjswj!jssszsgjswj!tcsgjswj!tcsgslfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"8330","ccode":"cssgsbfj","cname":"常熟市国税八分局","cparentid":"2013","cinnercode":"jssgjswj!jssszsgjswj!cssgjswj!cssgsbfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7785","ccode":"kssgsyfj","cname":"昆山市国税一分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgsyfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7786","ccode":"kssgsefj","cname":"昆山市国税二分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgsefj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7787","ccode":"kssgssfj","cname":"昆山市国税三分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgssfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7788","ccode":"kssgssfj","cname":"昆山市国税四分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgssfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7789","ccode":"kssgswfj","cname":"昆山市国税五分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgswfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"}]
        
    }
    
    @Test
    public void testGetInstallDetailByTemplate() { 
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("installTemplateId", "905840966170754569");
        map.put("installDocumentId", "222676258641485788");
        String installTemplateText = _helper.toJsonText(map, null);
        
        String resStr=service.getInstallDetailByTemplate(installTemplateText);
        
        Type type = new TypeToken<ArrayList<InstallDocuDetail>>() {}.getType();  
        List<InstallDocuDetail> list = _gson.fromJson(resStr, type);
        
        System.out.println(resStr);    //[{"cguid":"187011891516931394","cmainid":"187011891516931393","ccode":"1100791392","cname":"远程抄报软件","crelationmatid":"454832402342498298","ccontactid":"905840966170201044","cinstalldate":"Dec 10, 2013 12:00:00 AM","cismain":0,"cisstatus":1,"cservicedata":"Dec 10, 2013 12:00:00 AM","cservicestartdate":"Dec 10, 2013 12:00:00 AM","cguaranteestartdate":"Dec 10, 2013 12:00:00 AM","cguaranteeenddate":"Dec 10, 2013 12:00:00 AM"}]
    }
    
    @Test
    public void testGetEnterpriseContactInfo()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("cEnterpriseID", "108129");
        String enterpriseIdText = _helper.toJsonText(map, null);
        
        String resStr=service.getEnterpriseContactInfo(enterpriseIdText);
        System.out.println(resStr); //[{"cguid":"192972","centerpriseid":"23632","ccontactid":"126493","cisonjob":1,"ccreater":"1","ccreatedate":"Dec 7, 2013 12:16:47 AM","cupdater":"620634323740596598","cupdatedate":"Jan 1, 2014 6:19:46 PM","isnew":0,"contactInfo":{"cguid":"126493","ccode":"gmf1264","cname":"顾美芬","cphone3":"0","ctel1":"13375157506","ctel2":"13375157506","cadress":"昆山开发区黄河北路81号保昆公寓活动中心3楼西单元","ccreater":"1","ccreatedate":"Dec 7, 2013 12:16:47 AM","cupdater":"620634323740596598","cupdatedate":"Jan 1, 2014 6:19:46 PM"}}]
    }
    
    @Test
    public void testSelectAsCurrentContact()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("cguid", "192999");
        String currentContactText = _helper.toJsonText(map, null);
        String resStr = service.selectAsCurrentContact(currentContactText);
        System.out.println(resStr);
    }
    
    @Test
    public void testGetAllAreaInfo()
    {
        String resStr=service.getAllAreaInfo();
        System.out.println(resStr); 
    }
    
    @Test
    public void testGetEnterpriseAddresses()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("centerpriseid", "20110415140253670");
        System.out.println("------"+map.get("noKey"));
        String addresses = _helper.toJsonText(map, null);
        String resStr = service.getEnterpriseAddresses(addresses);
        System.out.println(resStr);
    }
    
    @Test
    public void testAddEnterpriseAddress()
    { 
        Map<String, String> map = new HashMap<String, String>();
        map.put("centerpriseid", "192999");
        map.put("cAdress", "test");
        map.put("cZipCode", "21000");
        map.put("cAreaID", "001");
        String addressText = _helper.toJsonText(map, null);
        String resStr = service.addEnterpriseAddress(addressText);
        System.out.println(resStr);
    }
    
    @Test
    public void testUpdateContactInfo()
    { 
    	Map<String, String> map = new HashMap<String, String>();
        map.put("cisonjob", "1");
        map.put("cguid", "126494");
        String contaxtText = _helper.toJsonText(map, null);
        String resStr = service.updateContactInfo(contaxtText);
        System.out.println(resStr);
    }
    
    @Test
    public void justTest()
    {
       /* String string = "{\"isauditingqualified\":null,\"cstatus\":1,\"ccode\":\"mobile821347265985\",\"ccreater\":\"1\",\"cinstalltypeid\":null,\"enterpriseBaseInfo\":null,\"remarks\":\"无\",\"ctaxcode\":\"320500692553043\",\"carea\":\"其它\",\"ccontactid\":\"192975\",\"customersignature\":\"王伯强\",\"centerpriseid\":\"103940\",\"ceventid\":null,\"cguid\":\"072157298052947556\",\"clastupdatedate\":null,\"cinstalldate\":\"2015-01-28 17:22:39\",\"centerpriseadress\":\"飞扬\",\"isauditingeffective\":null,\"employee\":null,\"centerprisedepartment\":null,\"ccreatedate\":\"2015-01-28 17:23:07\",\"cemployeeid\":\"145246972521717801\",\"caudittime\":null,\"cunsuccessresult\":\"无\",\"ccontacttel\":\"18951125918\",\"noqualifiedreason\":null,\"cinstalltype\":\"626498449016090763\",\"ctimestamp\":null,\"ccontactname\":\"王伯强\",\"cdepartment\":\"服务部\",\"ccontactphone\":\"18951125918\",\"clastupdater\":null,\"cbelongtax\":\"165120663464490419\",\"centerprisename\":\"苏州丽航国际货运代理有限公司\",\"cauditorguid\":null,\"cinstallresult\":1}";
        InstallDocument document = _gson.fromJson(string, InstallDocument.class);
        service.saveInstallDocument(string);
        System.out.println(document);*/
        
        StringBuilder s = new StringBuilder();
        s.append("133").append("344");
        System.out.println(s);
    }
    
}
