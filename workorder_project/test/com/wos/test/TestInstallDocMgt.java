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
        String eventCode = WosHelper.getInstance().toJsonText(map, null);
        
        String resStr=service.loadInstallDocumentByEventCode(eventCode);
        System.out.println(eventCode); //{"ceventid":"222"}
        System.out.println(resStr);    //{"cguid":"185054680023997827","ceventid":"222","ccode":"F00045381","centerpriseid":"20131108164948837","ctaxcode":"320503583758293","centerprisename":"苏州百家膳商贸有限公司","cbelongtax":"2004","centerpriseadress":"江苏省苏州市平江区北环东路170号","centerprisedepartment":"无","ccontactid":"177154","ccontactname":"吴伟","ccontacttel":"无","ccontactphone":"18912776261","cinstalltypeid":"465485634789821533","cinstalldate":"Dec 9, 2013 12:00:00 AM","cinstallresult":"1","cunsuccessresult":"无","cdepartment":"技服中心","carea":"姑苏区","cemployeeid":"379188785377422476","cstatus":1,"ccreater":"460144675295283761","ccreatedate":"Dec 9, 2013 8:45:20 PM","cauditorguid":"459799468148432191","caudittime":"Dec 11, 2013 1:52:55 PM","clastupdater":"459799468148432215","clastupdatedate":"Dec 10, 2013 3:46:50 PM","ctimestamp":"905840966170674524","cinstalltype":"271441751171043777","remarks":"无","isauditingqualified":1,"isauditingeffective":1,"customersignature":"吴伟"}
    }
    
    @Test
    public void testGetAllTaxOrganizations()
    {
        String resStr=service.getAllTaxOrganizations();
        System.out.println(resStr);     //[{"cguid":"165120663464490419","ccode":"szsguqgffj","cname":"苏州市姑苏区国税分局","corganizationcode":"1","cparentid":"2001","cinnercode":"jssgjswj!jssszsgjswj!szsguqgffj","ileaf":1,"ccreater":"1","ccreatedate":"Dec 9, 2013 3:11:52 PM","ctimestamp":"165120663464490418"},{"cguid":"102102","ccode":"szsdefj","cname":"苏州市第二国税分局","corganizationcode":"111","cparentid":"2001","cinnercode":"jssgjswj!jssszsgjswj!szsdefj","ileaf":1,"ccreater":"1","ccreatedate":"Dec 25, 2013 2:54:55 PM","ctimestamp":"102780964199954128","cfullname":"苏州市第二分局"},{"cguid":"920623803615892447","ccode":"00001","cname":"其他","corganizationcode":"0001","cparentid":"000000","cinnercode":"00001","ileaf":1,"ccreater":"1","ccreatedate":"Jan 17, 2014 2:16:27 PM","ctimestamp":"920623803615892446","cfullname":"其他"},{"cguid":"8329","ccode":"tcsgslfj","cname":"太仓市国税六分局","cparentid":"2032","cinnercode":"jssgjswj!jssszsgjswj!tcsgjswj!tcsgslfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"8330","ccode":"cssgsbfj","cname":"常熟市国税八分局","cparentid":"2013","cinnercode":"jssgjswj!jssszsgjswj!cssgjswj!cssgsbfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7785","ccode":"kssgsyfj","cname":"昆山市国税一分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgsyfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7786","ccode":"kssgsefj","cname":"昆山市国税二分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgsefj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7787","ccode":"kssgssfj","cname":"昆山市国税三分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgssfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7788","ccode":"kssgssfj","cname":"昆山市国税四分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgssfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"},{"cguid":"7789","ccode":"kssgswfj","cname":"昆山市国税五分局","cparentid":"2023","cinnercode":"jssgjswj!jssszsgjswj!kssgjswj!kssgswfj","ileaf":1,"ccreater":"1","ccreatedate":"Nov 18, 2013 8:23:12 PM"}]
        
    }
    
}
