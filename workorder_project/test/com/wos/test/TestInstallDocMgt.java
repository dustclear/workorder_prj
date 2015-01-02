package com.wos.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.wos.common.WosHelper;
import com.wos.mgt.InstallDocMgt;

public class TestInstallDocMgt
{
    private static InstallDocMgt service;
    private WosHelper _helper = WosHelper.getInstance();
    
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
        map.put("ceventid", "222");
        String eventCode = _helper.toJsonText(map, null);
        
        String resStr=service.loadInstallDocumentByEventCode(eventCode);
        //注意resStr的输出结果，这是一个复杂的数据结构，其中包含了最深三层的结构：installdocument~enterpriseBaseInfo~currentContact~contactInfo,前端解析时请注意。。
        System.out.println(eventCode); //{"ceventid":"222"}
        System.out.println(resStr);    //{"cguid":"185054680023997827","ceventid":"222","ccode":"F00045381","centerpriseid":"20121122110027770","ctaxcode":"320503583758293","centerprisename":"苏州百家膳商贸有限公司","cbelongtax":"2004","centerpriseadress":"江苏省苏州市平江区北环东路170号","centerprisedepartment":"无","ccontactid":"177154","ccontactname":"吴伟","ccontacttel":"无","ccontactphone":"18912776261","cinstalltypeid":"465485634789821533","cinstalldate":"Dec 9, 2013 12:00:00 AM","cinstallresult":"1","cunsuccessresult":"无","cdepartment":"技服中心","carea":"姑苏区","cemployeeid":"379188785377422476","cstatus":1,"ccreater":"460144675295283761","ccreatedate":"Dec 9, 2013 8:45:20 PM","cauditorguid":"459799468148432191","caudittime":"Dec 11, 2013 1:52:55 PM","clastupdater":"459799468148432215","clastupdatedate":"Dec 10, 2013 3:46:50 PM","ctimestamp":"905840966170674524","cinstalltype":"271441751171043777","remarks":"无","isauditingqualified":1,"isauditingeffective":1,"customersignature":"吴伟","enterpriseBaseInfo":{"cguid":"20121122110027770","ccode":"1","cname":"苏州丽航国际货运代理有限公司","cfastcode":"szlhgjhydlyxgs","ctypeid":"000000000000000001","ctel1":"68322575","ctaxcode":"320500692553043","careaid":"705106892172137892","ctaxationid":"2035","cregisteredaddress":"苏州市珠江南路378号天隆大厦447","cisoverdue":0,"ccreater":"1","ccreatedate":"Dec 6, 2013 11:24:44 PM","crmid":"9560723","currentAddress":{"cguid":"361521","centerpriseid":"20121122110027770","cadress":"玉山镇江浦路307号","czipcode":"215000","cadresstype":"000000000000000001","cisvalid":1,"ccreatedate":"Jan 1, 1900 12:00:00 AM","cupdatedate":"Jan 1, 1900 12:00:00 AM","isnew":1,"crmid":"21145732"},"currentContact":{"cguid":"192974","centerpriseid":"20121122110027770","ccontactid":"126495","ccreater":"1","ccreatedate":"Dec 7, 2013 12:33:02 AM","isnew":1,"contactInfo":{"cguid":"126495","ccode":"wyq1778","cname":"王云秋","cphone1":"13621571778","cphone2":"","cphone3":"1","ctel1":"63552328","ctel2":"0512-63552328","ctel5":"","cadress":"市场路191号","cidcard":"","cemail":"","ccreater":"1","ccreatedate":"Dec 7, 2013 12:33:02 AM"}}}}
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
        map.put("cmainid", "187011891516931393");
        map.put("installTemplateId", "271441751171043777");
        String installTemplateText = _helper.toJsonText(map, null);
        
        String resStr=service.getInstallDetailByTemplate(installTemplateText);
        System.out.println(resStr);    //[{"cguid":"187011891516931394","cmainid":"187011891516931393","ccode":"1100791392","cname":"远程抄报软件","crelationmatid":"454832402342498298","ccontactid":"905840966170201044","cinstalldate":"Dec 10, 2013 12:00:00 AM","cismain":0,"cisstatus":1,"cservicedata":"Dec 10, 2013 12:00:00 AM","cservicestartdate":"Dec 10, 2013 12:00:00 AM","cguaranteestartdate":"Dec 10, 2013 12:00:00 AM","cguaranteeenddate":"Dec 10, 2013 12:00:00 AM"}]
    }
    
    @Test
    public void testGetEnterpriseContactInfo()
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("cEnterpriseID", "23632");
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
    
}
